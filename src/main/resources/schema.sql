DROP TABLE IF EXISTS currency_request;
CREATE TABLE currency_request
(
    id      BIGSERIAL PRIMARY KEY,
    request JSON
);

DROP TABLE IF EXISTS currency_response;
CREATE TABLE currency_response
(
    id         BIGSERIAL PRIMARY KEY,
    response   JSON,
    currency_request_id BIGINT REFERENCES currency_request (id)
);

