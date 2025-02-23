FROM postgres:15.1-alpine

ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=password
ENV POSTGRES_DB=security

COPY ./scripts/ /docker-entrypoint-initdb.d