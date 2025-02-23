#!/bin/sh

# creating needed environment variables to be able to execute vault commands
#export VAULT_ADDR='http://127.0.0.1:8200'
#export VAULT_TOKEN=secret_bro

# creating secrets
vault secrets enable -path=spring-cloud kv
vault kv put spring-cloud/api-secrets access-secret=Vmtkb2NHTjcVRbkJqZVVKb1NVaE9iRmt6U214a1EwSnRZak5KWjFsWFRtcGFXRTU2U1VoU2RtRXlWblU9 refresh-secret=Vmtkb2NHTjVRbkJqZVVKb1NVaE9iRmt6U214a1EwSnRZak5KWjJOdFZtMWpiVlo2WVVOQ01HSXlkR3hpWnowOQ==