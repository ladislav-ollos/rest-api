# rest-api

A small demo project with a rest API providing CRUD functionality over a small DB.

It is a spring boot application with H2 database, listens on port 3000. OpenApi documentation can be found under:
http://localhost:3000/v3/api-docs.yaml

Ships with a swagger ui:
http://localhost:3000/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

## API and design decisions

### URLs and parameters

I was considering nesting the subscriptionDto endpoint under userDto and calling POST put and delete
on `/userDto/{id}/subscriptionDto`, but a separate `/subscriptionDto is more flexible`
also the post on `/subscriptionDto` could use request body to ship the IDs.

### HTTP methods

In the case of `/subscriptionDto/{id}/unpause` and `/subscriptionDto/{id}/pause` I used paypal as an inspiration they
designed it in the same fashion.
`PUT` or `PATCH` with some URL could be an alternative to consider.
The cancel operation was implemented as a delete, but depending on whether or not we want to keep track of canceled
subscriptions it could be a different implementation. In real life we probably would like to keep them, but for a demo a
delete is OK.

### Error handling

`NotFoundException` is a small example of how it could be done, but for the sake of brevity I omitted it in the rest of
the demo.

### OpenApi

The schema could be documented in a lot more detail but again it is a demo.
