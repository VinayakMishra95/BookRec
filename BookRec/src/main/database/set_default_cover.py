import psycopg
import os

# Configuration of connection to the database
conn = psycopg.connect(
    dbname="bookrec",
    user="postgres",
    password="postgres",
    host="localhost",
    port="5432"
)

# Function to read file name
def read_image(file_path):
    with open(file_path, 'rb') as file:
        image_data = file.read()
    return image_data

# Image path
image_path = 'default_cover.jpg'

# Read the image
image_data = read_image(image_path)
_, file_ext = os.path.splitext(image_path)

# Statement to be performed
update_query = """
        UPDATE books
        SET cover = %s, coverExt = %s
        WHERE cover IS NULL;
        """


# Insert it into the database
try:
    with conn.cursor() as cur:
        # Execute query
        cur.execute(update_query, (psycopg.Binary(image_data), file_ext))
        conn.commit()
except Exception as e:
    print(f"Errore durante l'inserimento: {e}")
    conn.rollback()
finally:
    conn.close()

