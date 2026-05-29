REST API aplikacji *Klockowisko* do zarządzania zabawkami.

| | |
|---|---|
| **Bazowy URL** | `http://localhost:8080` |
| **Format** | JSON|
| **Baza danych** | PostgreSQL |

---

## Uwierzytelnianie

Większość endpointów wymaga tokenu JWT.

1. Zarejestruj użytkownika (`POST /api/auth/register`) lub użyj istniejącego konta.
2. Zaloguj się (`POST /api/auth/login`) - otrzymasz w odpowiedzi token.
3. Do kolejnych żądań dodaj nagłówek:

```http
Authorization: Bearer <token>
```

Token jest ważny godzinę. Endpointy pod `/api/auth/**` są publiczne (bez tokenu).

**Role użytkownika:** `USER`, `ADMIN` (podawana przy rejestracji).

---

## Błędy

### Walidacja (422)

Gdy dane wejściowe nie przejdą walidacji (`@Valid`):

```json
{
  "message": "Błąd walidacji",
  "status": 422,
  "errors": {
    "name": "Name must be between 3 and 192 characters long"
  }
}
```

### Brak zasobu (404)

```json
{
  "message": "Resource not found",
  "status": 404
}
```

### Brak lub nieprawidłowy token (401)

```json
{
  "error": "Unauthorized - invalid or missing token",
  "status": 401
}
```

### Brak uprawnień (403)

```json
{
  "status": 403,
  "error": "Forbidden"
}
```

---

## Uwierzytelnianie

### Rejestracja

`POST /api/auth/register`

**Body:**

| Pole | Typ | Wymagane | Opis |
|------|-----|----------|------|
| `username` | string | tak | Nazwa użytkownika |
| `email` | string | tak | Adres e-mail |
| `password` | string | tak | Hasło (hashowane BCrypt) |
| `role` | string | tak | `USER` lub `ADMIN` |

**Odpowiedź:** `200 OK` - tekst: `"User registered"`

**Przykład:**

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "jan",
    "email": "jan@example.com",
    "password": "haslo123",
    "role": "USER"
  }'
```

---

### Logowanie

`POST /api/auth/login`

**Body:**

| Pole | Typ | Wymagane |
|------|-----|----------|
| `username` | string | tak |
| `password` | string | tak |

**Odpowiedź:** `200 OK`

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

**Przykład:**

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "jan", "password": "haslo123"}'
```

---

## Producenci

Prefix: `/api/manufacturers` - **wymaga JWT**

### Lista producentów

`GET /api/manufacturers`

**Odpowiedź:** `200 OK` - tablica obiektów:

```json
[
  { "id": 1, "name": "LEGO" }
]
```

---

### Pojedynczy producent

`GET /api/manufacturers/{id}`

**Odpowiedź:** `200 OK`

```json
{ "id": 1, "name": "LEGO" }
```

**Błędy:** `404` - producent nie istnieje

---

### Utworzenie producenta

`POST /api/manufacturers`

**Body:**

| Pole | Typ | Wymagane | Walidacja                                       |
|------|-----|----------|-------------------------------------------------|
| `name` | string | tak | 3–64 znaków                                     |
| `id` | number | nie | ignorowane przy tworzeniu (używane przy edycji) |

**Odpowiedź:** `200 OK` - utworzony producent (`id`, `name`)

**Przykład:**

```bash
curl -X POST http://localhost:8080/api/manufacturers \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"name": "LEGO"}'
```

---

### Usunięcie producenta
Powodzenie nie zależy od istnienia producenta.

`DELETE /api/manufacturers/{id}`

**Odpowiedź:** `200 OK`

```json
{
  "message": "Success",
  "status": 200
}
```

---

## Zabawki

Prefix: `/api/toys` - **wymaga JWT**

### Lista zabawek

`GET /api/toys`

**Odpowiedź:** `200 OK` - tablica:

```json
[
  {
    "id": 1,
    "name": "Klocki Creator",
    "manufacturer": { "id": 1, "name": "LEGO" },
    "owner": { "id": 1, "username": "jan" },
    "purchaseDate": "2024-06-15T00:00:00.000+00:00",
    "price": 149.99
  }
]
```

Pole `manufacturer` może być `null`, jeśli zabawka nie ma przypisanego producenta.

---

### Pojedyncza zabawka

`GET /api/toys/{id}`

**Odpowiedź:** jak pojedynczy element listy powyżej.

**Błędy:** `404` - `"Resource not found"`

---

### Utworzenie zabawki

`POST /api/toys`

**Body:**

| Pole | Typ | Wymagane | Walidacja                                       |
|------|-----|----------|-------------------------------------------------|
| `name` | string | tak | 3–192 znaków                                    |
| `ownerId` | number | tak | ID istniejącego użytkownika                     |
| `manufacturerId` | number | nie | ID producenta                                   |
| `purchaseDate` | string (ISO-8601) | nie | data zakupu                                     |
| `price` | number | nie | >= 0                                            |
| `id` | number | nie | ignorowane przy tworzeniu (używane przy edycji) |

**Odpowiedź:** `200 OK` - utworzona zabawka (pełny obiekt `ToyResponse`)

**Przykład:**

```bash
curl -X POST http://localhost:8080/api/toys \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
    "name": "Klocki Creator",
    "ownerId": 1,
    "manufacturerId": 1,
    "price": 149.99
  }'
```

---

### Usunięcie zabawki
Powodzenie nie zależy od istnienia zabawki.

`DELETE /api/toys/{id}`

**Odpowiedź:** `200 OK`

```json
{
  "message": "Success",
  "status": 200
}
```

---

## Podsumowanie endpointów

| Metoda | Ścieżka | Wymaga uwierzytelnienia | Opis                    |
|--------|---------|-------------------------|-------------------------|
| `POST` | `/api/auth/register` | nie                     | Rejestracja użytkownika |
| `POST` | `/api/auth/login` | nie                     | Logowanie, zwrot JWT    |
| `GET` | `/api/manufacturers` | tak                     | Lista producentów       |
| `GET` | `/api/manufacturers/{id}` | tak                     | Szczegóły producenta    |
| `POST` | `/api/manufacturers` | tak                     | Nowy producent/edycja   |
| `DELETE` | `/api/manufacturers/{id}` | tak                     | Usunięcie producenta    |
| `GET` | `/api/toys` | tak                     | Lista zabawek           |
| `GET` | `/api/toys/{id}` | tak                     | Szczegóły zabawki       |
| `POST` | `/api/toys` | tak                     | Nowa zabawka/edycja     |
| `DELETE` | `/api/toys/{id}` | tak                     | Usunięcie zabawki       |

---

## Uruchomienie (skrót)

1. Uruchom PostgreSQL:
    ```sh
    # to plik run_cheatsheet.sh z katalogu "postgres"
    # zbudowanie kontenera
    docker build -t klockowisko-postgres .
    # utworzenie woluminu dla trwałości danych pomiędzy restartami
    docker volume create pgdata
    # uruchomienie kontenera (port 2137)
    docker run -d \
    --name klockowisko-postgres \
    -p 2137:5432 \
    -v pgdata:/var/lib/postgresql/data \
    klockowisko-postgres 
   ```
2. Uruchom aplikację (najlepiej w Intellij IDEA, inne metody nie zostały sprawdzone)

---

## Samoocena

**Ocena 3.0 (Dostateczny):**
- [x] Działający Spring Boot
- [x] Połączenie z bazą danych
- [x] CRUD dla 1 encji
- [x] Demo video pokazuje podstawowe działanie

**Ocena 4.0 (Dobry):**
- [x] Wszystko z 3.0 +
- [x] Poprawna struktura warstwowa (Controller/Service/Repository)
- [x] DTO + Walidacja danych
- [x] Obsługa błędów
- [x] Security (JWT lub Basic Auth)

**Ocena 5.0 (Bardzo dobry):**
- [ ] Wszystko z 4.0 +
- [ ] Unit Testy
- [ ] Events LUB Kolejki (Rabbit/Kafka)
- [ ] Czysty kod
- [ ] Frontend (prosta aplikacja Angular konsumująca API)

**Wymagania formalne:**
- [ ] Demo video 2-3 min pokazujące działanie aplikacji
- [ ] Link do repozytorium GitHub
- [ ] Dokumentacja uruchomienia (README.md)# Klockowisko - dokumentacja API

