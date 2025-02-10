FROM postgres:15.1-alpine

ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=password
ENV POSTGRES_DB=history

COPY ./scripts/ /docker-entrypoint-initdb.d