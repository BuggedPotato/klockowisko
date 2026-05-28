docker build -t klockowisko-postgres .

docker volume create pgdata

docker run -d \
  --name klockowisko-postgres \
  -p 2137:5432 \
  -v pgdata:/var/lib/postgresql/data \
  klockowisko-postgres