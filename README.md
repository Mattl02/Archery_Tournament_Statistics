# Archery_Tournament_Statistics
Travis CI: Build #14 passed (Commit 6a499f0, Branch: master)

Database access:
user=postgres | password=postgres

dependencies:
postgresql-9.1 driver

Before running the program, one most restore the backup located under "database_backup/backup".
When running the program, you are immediately prompted for a database name. After entering said name, tournaments, etc.
should load from the database. This is a temporary solution to allow using this program with a local database.
