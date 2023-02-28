# POST /api/v1/crm/tracking/intents
curl -i -X POST -H "Content-Type:application/json" http://local.gtaaustin.com/api/v1/crm/tracking/intents -d '{"topic":"cta-advice"}'

# POST /api/v1/crm/leads
curl -i -X POST -H "Content-Type:application/json" http://local.gtaaustin.com/api/v1/crm/leads -d '{ "name": "Mike Smith", "email": "businessguy@example.com", "phone": "555-123-1212", "subject": "Please reach out, need strategic advice", "message": "Interested in setting up a new team. No idea where to start."}'
