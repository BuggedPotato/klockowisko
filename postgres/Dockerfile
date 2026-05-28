FROM postgres:16

ENV POSTGRES_USER=myuser
ENV POSTGRES_PASSWORD=mypassword
ENV POSTGRES_DB=klockowisko

VOLUME ["/var/lib/postgresql/data"]

EXPOSE 5432

# COPY ./init.sql /docker-entrypoint-initdb.d/