import mysql.connector
from mysql.connector import Error

import random
from datetime import date, datetime, timedelta

def generate_random_time():
    """Génère une heure aléatoire pour les créneaux."""
    start = datetime.now().replace(hour=8, minute=0, second=0, microsecond=0)
    end = start + timedelta(minutes=random.choice(range(8)) * 30)  # créneaux de 30 minutes entre 8h et 12h
    return end.time()

def add_creneaux_disponibles(cursor, doc_id, nb_creneaux=5):
    """Ajoute des créneaux disponibles pour un docteur donné."""
    for _ in range(nb_creneaux):
        date_creneau = date.today() + timedelta(days=random.randint(1, 30))
        debut = generate_random_time()
        fin = (datetime.combine(date.min, debut) + timedelta(hours=1)).time()  # Durée d'1 heure
        cursor.execute("""
        INSERT INTO CreneauDisponible (doc_id, date, debut, fin, statut)
        VALUES (%s, %s, %s, %s, 'disponible')
        """, (doc_id, date_creneau, debut, fin))

def add_rendez_vous(cursor, creneau_id, patient_id, doc_id):
    """Ajoute un rendez-vous en utilisant un créneau disponible."""
    cursor.execute("""
    INSERT INTO RendezVous (creneau_id, patient_id, doc_id, statut, date_prise_rdv)
    VALUES (%s, %s, %s, 'confirme', NOW())
    """, (creneau_id, patient_id, doc_id))

def connect_to_db():
    try:
        connection = mysql.connector.connect(
            host='localhost',  # ou votre hôte
            database='ProjetJava',
            user='pierre',
            password='',
            port=8080)
        if connection.is_connected():
            return connection
    except Error as e:
        print("Erreur lors de la connexion à MySQL", e)
        return None

def get_doc_ids(cursor):
    """Récupère tous les doc_id de la table Docteur."""
    cursor.execute("SELECT doc_id FROM Docteur")
    return [row[0] for row in cursor.fetchall()]

def insert_test_data():
    connection = connect_to_db()
    if connection is None:
        return
    
    cursor = connection.cursor()

    # Récupérer les doc_id existants
    doc_ids = get_doc_ids(cursor)

    
    # Insérer des données dans Utilisateur
    for i in range(10):  # Générer 10 utilisateurs de chaque type
        for role in ['patient', 'docteur', 'employe', 'administrateur']:
            try:
                cursor.execute("""
                INSERT INTO Utilisateur (email, mot_de_passe, role)
                VALUES (%s, %s, %s)
                """, (f"{role}{i}@exemple.com", "motdepassehash", role))
            except Error as e:
                print(f"Erreur lors de l'insertion dans Utilisateur: {e}")
                connection.rollback()
    
    connection.commit()
    
    # Supposer que l'ID utilisateur commence à 1 et est incrémenté séquentiellement
    # Insérer des données dans Patient, Docteur, et Employe en utilisant l'utilisateur_id
    utilisateur_id = 1
    for i in range(10):
        try:
            # Insérer dans Patient
            cursor.execute("""
            INSERT INTO Patient (utilisateur_id, nom, prenom, age)
            VALUES (%s, %s, %s, %s)
            """, (utilisateur_id, "NomPatient", "Prenom", 30))
            utilisateur_id += 1
            
            # Insérer dans Docteur
            cursor.execute("""
            INSERT INTO Docteur (utilisateur_id, nom, prenom, specialite, lieu)
            VALUES (%s, %s, %s, %s, %s)
            """, (utilisateur_id, "NomDocteur", "Prenom", "Generaliste", "Lieu"))
            utilisateur_id += 1
            
            # Insérer dans Employe
            cursor.execute("""
            INSERT INTO Employe (utilisateur_id, nom, prenom)
            VALUES (%s, %s, %s)
            """, (utilisateur_id, "NomEmploye", "Prenom"))
            utilisateur_id += 1
            
            # Sauter l'insertion pour administrateur
            utilisateur_id += 1
            
        except Error as e:
            print(f"Erreur lors de l'insertion dans Patient/Docteur/Employe: {e}")
            connection.rollback()
    
    connection.commit()
    
    # Ajout des créneaux disponibles pour chaque docteur
    doc_ids = range(2, 21, 4)  # IDs des docteurs basés sur l'insertion précédente
    for doc_id in doc_ids:
        add_creneaux_disponibles(cursor, doc_id)

    connection.commit()

    # Ajout de rendez-vous
    # Nous utiliserons les IDs des patients et des docteurs de façon arbitraire ici pour la démo
    # Vous devriez adapter ces IDs en fonction de ceux présents dans votre base de données
    patient_ids = range(1, 21, 4)  # IDs des patients
    for patient_id, doc_id in zip(patient_ids, doc_ids):
        # Prend le premier créneau disponible pour le docteur
        cursor.execute("SELECT creneau_id FROM CreneauDisponible WHERE doc_id = %s AND statut = 'disponible' LIMIT 1", (doc_id,))
        creneau_id = cursor.fetchone()[0]
        if creneau_id:
            add_rendez_vous(cursor, creneau_id, patient_id, doc_id)
            # Marquer le créneau comme réservé
            cursor.execute("UPDATE CreneauDisponible SET statut = 'reserve' WHERE creneau_id = %s", (creneau_id,))
    
    connection.commit()
    
    cursor.close()
    connection.close()


if __name__ == "__main__":
    insert_test_data()
