# Rewards API

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
dollar spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
 
Given a record of every transaction during a three month period, calculate the reward points earned for
each customer per month and total.

The API has the following endpoints:

# POST /customer
Creates a new Customer record

# GET /customer/{customerId}
Retrieves a Customer record by its ID

# POST /customer/{customerId}/transactions
Adds a new Transaction to a Customer record

# GET /customer/{customerId}/transactions
Retrieves all Transactions of a Customer

# GET /customer/{customerId}/rewards
Retrieves all the information regarding the Reward points of a Customer
