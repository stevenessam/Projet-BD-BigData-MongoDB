from faker import Faker
from pymongo import MongoClient
from datetime import datetime, timedelta
import random
import decimal
from bson import ObjectId
import string

# Connexion à MongoDB
client = MongoClient('mongodb://localhost:27017/')
database = client['Projet_BD_Groupe6_light']

# Initialisation de Faker
fake = Faker()

# Conversion des objets Decimal en float
def convert_decimal_to_float(obj):
    if isinstance(obj, decimal.Decimal):
        return float(obj)
    return obj


# Génération de données pour la table Clients
def generate_fake_clients(num_clients):
    clients = []
    for i in range(num_clients):
        client_data = {
            '_id': i + 1,  # Commencer les IDs à partir de 1
            'Nom': fake.last_name(),
            'Prenom': fake.first_name(),
            'Email': fake.email(),
            'Telephone': f"06{''.join(random.choices(string.digits, k=8))}",
            'SoldeCompte': round(random.uniform(0, 200), 2),
            'DerniereLocalisation': {
                'longitude': convert_decimal_to_float(fake.longitude()),
                'latitude': convert_decimal_to_float(fake.latitude())
            }
        }
        clients.append(client_data)
    return clients


# Génération de données pour la table Trottinettes
def generate_fake_trottinettes(num_trottinettes):
    trottinettes = []
    for i in range(num_trottinettes):
        etat_batterie = random.randint(1, 100)
        decalage_minutes = random.randint(1, 100) - 7
        decalage_minutes *= 2.17

        dernier_check = generate_fake_datetime(
            end_date=f'-{int(abs(decalage_minutes))}m')

        trottinette_data = {
            '_id': i + 1,  # Commencer les IDs à partir de 1
            'EtatBatterie': etat_batterie,
            'Disponibilite': etat_batterie > 7,
            'Localisation': {
                'longitude': convert_decimal_to_float(fake.longitude()),
                'latitude': convert_decimal_to_float(fake.latitude())
            },
            'DernierCheck': dernier_check,
            'AvisID': []  # Initialiser à une liste vide
        }
        trottinettes.append(trottinette_data)
    return trottinettes

# Génération de données pour la table Avis
def generate_fake_avis(num_avis, clients, trottinettes):
    avis = []
    for i in range(num_avis):
        client_id = random.choice([client['_id'] for client in clients])
        trottinette_id = random.choice(
            [trottinette['_id'] for trottinette in trottinettes])
        message_avis = fake.paragraph()
        avis_data = {
            '_id': i + 1,  # Commencer les IDs à partir de 1
            'ClientID': client_id,
            'TrottinetteID': trottinette_id,
            'MessageAvis': message_avis
        }
        avis.append(avis_data)

        # Assurez-vous d'ajouter ces IDs aux documents correspondants dans les collections Clients et Trottinettes
        database['Clients'].update_one(
            {'_id': client_id}, {'$addToSet': {'AvisID': avis_data['_id']}})
        database['Trottinettes'].update_one(
            {'_id': trottinette_id}, {'$addToSet': {'AvisID': avis_data['_id']}})

    return avis


# Génération de données pour la table Reservations
def generate_fake_reservations(num_reservations, clients, trottinettes):
    reservations = []
    for i in range(num_reservations):
        client_id = random.choice([client['_id'] for client in clients])
        trottinette_id = random.choice(
            [trottinette['_id'] for trottinette in trottinettes])
        date_heure_debut = generate_fake_datetime()
        date_heure_fin = date_heure_debut + \
            timedelta(minutes=random.randint(30, 180))
        duree_minutes = (date_heure_fin - date_heure_debut).seconds // 60
        duree_demi_heures = -(-duree_minutes // 30)
        # Arrondir à 2 chiffres après la virgule
        tarif_value = round(max(random.uniform(0, 50), 0), 2)
        reservation_data = {
            '_id': i + 1,  # Commencer les IDs à partir de 1
            'ClientID': client_id,
            'TrottinetteID': trottinette_id,
            'DateHeureDebut': date_heure_debut,
            'DateHeureFin': date_heure_fin,
            'Tarif': tarif_value
        }
        reservations.append(reservation_data)
    return reservations


# Génération de données pour la table Transactions
def generate_fake_transactions(num_transactions, clients):
    transactions = []
    for i in range(num_transactions):
        client_id = random.choice([client['_id'] for client in clients])
        # Arrondir à 2 chiffres après la virgule
        montant = round(max(random.uniform(5, 50), 0), 2)
        montant = min(montant, 200)
        date_transaction = generate_fake_datetime()
        type_transaction = random.choice(
            ['Recharge de solde', 'Paiement de location'])
        transaction_data = {
            '_id': i + 1,  # Commencer les IDs à partir de 1
            'ClientID': client_id,
            'Montant': montant,
            'DateTransaction': date_transaction,
            'TypeTransaction': type_transaction
        }
        transactions.append(transaction_data)
    return transactions


# Fonction pour générer une fausse date et heure
def generate_fake_datetime(start_date='-30d', end_date='now'):
    return fake.date_time_between(start_date=start_date, end_date=end_date)


# Génération et insertion des données dans MongoDB
# num_clients = 2000
# num_trottinettes = 2000
# num_avis = 5000
# num_reservations = 1000
# num_transactions = 1000


num_clients = 20
num_trottinettes = 20
num_avis = 50
num_reservations = 10
num_transactions = 10


clients = generate_fake_clients(num_clients)
trottinettes = generate_fake_trottinettes(num_trottinettes)
avis = generate_fake_avis(num_avis, clients, trottinettes)
reservations = generate_fake_reservations(
    num_reservations, clients, trottinettes)
transactions = generate_fake_transactions(num_transactions, clients)

database['Clients'].insert_many(clients)
database['Trottinettes'].insert_many(trottinettes)
database['Avis'].insert_many(avis)
database['Reservations'].insert_many(reservations)
database['Transactions'].insert_many(transactions)

# Mettez à jour les ID d'avis dans la collection Trottinettes
for trottinette in trottinettes:
    trottinette_id = trottinette['_id']
    avis_ids = [avis['_id']
                for avis in avis if avis['TrottinetteID'] == trottinette_id]
    database['Trottinettes'].update_one(
        {'_id': trottinette_id}, {'$set': {'AvisID': avis_ids}})

# Fermeture de la connexion à MongoDB
client.close()
