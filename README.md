API Endpoints

-------------------
**Admin Endpoints**
-------------------

**Get All Accounts:**

Method: GET

URL: http://localhost:8082/api/bank/accounts

**Get All Checking Accounts:**

Method: GET

URL: http://localhost:8082/api/bank/checking

**Get All Savings Accounts:**

Method: GET

URL: http://localhost:8082/api/bank/savings

**Get All Transactions:**

Method: GET

URL: http://localhost:8082/api/bank/txn/all

**Get Transactions by Checking ID & Account ID:**

Method: GET

URL: http://localhost:8082/api/bank/txn/1/1

-------------------
**User Endpoints**
-------------------

**Get Checking Accounts by Account ID:**

Method: GET

URL: http://localhost:8082/api/bank/checking/1

**Get Savings Accounts by Account ID:**

Method: GET

URL: http://localhost:8082/api/bank/savings/1

**Get Transactions by Checking ID:**

Method: GET

URL: http://localhost:8082/api/bank/txn/checking/2

**Get Transactions by Savings ID:**

Method: GET

URL: http://localhost:8082/api/bank/txn/savings/1

-------------------
**Create Endpoints**
-------------------

**Create User Account:**

Method: POST

URL: http://localhost:8082/api/bank/accounts/create

Body: { "name": "name of user" }

**Create Checking Account:**

Method: POST

URL: http://localhost:8082/api/bank/checking/create

Body: { "accountName": "checking account name", "userName": "name of user" }

**Create Savings Account:**

Method: POST

URL: http://localhost:8082/api/bank/savings/create

Body: { "accountName": "savings account name", "userName": "name of user" }

-------------------
**Delete Endpoints**
-------------------

**Delete Checking Account by Name:**

Method: DELETE

URL: http://localhost:8082/api/bank/checking/delete?name={checking_account_name}

**Delete Savings Account by Name:**

Method: DELETE

URL: http://localhost:8082/api/bank/savings/delete?name={savings_account_name}
