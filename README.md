ODBaaS
======
ODBaaS is a free Database service which is set up to eliminate the need for deploying and managing dedicated database hardware and software for every individual project environment. In contrast to existing DBaaS implementations, our service is specifically designed to provide a simplified one stop service for your Create, Read, Update and Delete requests directed to our database.

**Version:** 1.0.0

# Releases

You may use our already existing endpoint mentioned [here](https://documenter.getpostman.com/view/2174475/odbaas/7E8hFgT). Or if that's not available you may look at the release we offer [here](https://github.com/shreyash14s/odbaas/releases/tag/v1.0).

# Contributors

* [Sanjay Josh](https://github.com/SanjayJosh)

* [Shreyash Sarnayak](https://github.com/shreyash14s)

* [Shuaib Ur Rahman](https://github.com/shuaib47)

* [Siddharth Srinivasan](https://github.com/siddharths2710)


# Specification

### /login/
---
##### ***POST***
**Summary:** Logs in the user to the DBaaS, and creates a DB for the user if it doesn't exist.

**Description:** 

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| user_name | formData | username associated with the user in the AnSu-IAM service. | Yes | string |
| password | formData | Password associated with the user in the AnSu-IAM service. | Yes | string |
| outputFormat | formData | The mode of sending a response. ( defaults to json ) | No | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | Successfully logged in to database |
| 401 | Unauthorized access to Database |
| 404 | Invalid userId supplied |
| 500 | DB already exists |

### /table/create/{table_name}
---
##### ***POST***
**Summary:** Create a table in a specified db for a particular user

**Description:** 

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| table_name | path | name of table to be created | Yes | string |
| token | formData | The token that is generated from the IAM token management system. | Yes | string |
| schema | formData | Comma separated sentences  where each sentence is a key-value pair ( colon separated ) ; key is the column name and value is constraints for that column. Ex. "id:INT NOT NULL,name:VARCHAR(3)" | Yes | string |
| primary_key | formData | the set of attributes that serve as the primary key of the table (Give as comma separated values) Ex. "id,name" | No | string |
| outputFormat | formData | The mode of sending a response. ( defaults to json ) | No | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | Successfully created a table |
| 401 | Unauthorized access to Database |
| 500 | The error reported in creating a table |

### /table/insert/{table_name}
---
##### ***POST***
**Summary:** Insert values in the database

**Description:** 

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| table_name | path | name of table to be created | Yes | string |
| token | formData | The token that is generated from the IAM token management system. | Yes | string |
| data | formData | A JSONArray where each JSON contains the column names as keys and the column values as the corresponding value. ( Column names have to be fixed for each and every JSON ). | Yes | string |
| outputFormat | formData | The mode of sending a response. ( defaults to json ) | No | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | Successfully inserted into a table ( Returns number of records inserted ) |
| 401 | Unauthorized access to Database |
| 500 | The error reported in inserting into a table |

### /select
---
##### ***GET***
**Summary:** Retrieve attribute values from tables

**Description:** 

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| table_name | query | name of table to be created | Yes | string |
| token | query | The token that is generated from the IAM token management system. | Yes | string |
| columns | query | Comma separated values denoting the column values to be shown ( defaults to "*" ). | Yes | string |
| where | query | Comma separated key-value pairs where key denotes conjunction (AND) or disjunction (OR), and the value denotes the condition | No | string |
| sort_cols | query | Individual ( or comma separated ) column names | No | string |
| sort_order | query | May be ASC or DESC ( defaults to ASC if sort_cols is provided ). | No | string |
| limit | query | The max. no. of records to output ( Should be a number ). | No | string |
| outputFormat | query | The mode of sending a response. ( defaults to json ) | No | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | The data required is returned |
| 401 | Unauthorized access to Database |
| 500 | The error reported in selecting to a table |

### /update/{table_name}
---
##### ***PUT***
**Summary:** Update given record(s) in the table

**Description:** 

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| table_name | path | name of table to be created | Yes | string |
| set | formData | key_value_pairs where key is the column and value is the corresponding value | Yes | string |
| where | formData | key_value_pairs where key is the column and value is the corresponding value | No | string |
| token | formData | The token that is generated from the IAM token management system. | Yes | string |
| outputFormat | formData | The mode of sending a response. ( defaults to json ) | No | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | The table is updated |
| 401 | Unauthorized access to Database |
| 500 | The error reported in updating a table |

### /delete/{table_name}
---
##### ***DELETE***
**Summary:** Update given record(s) in the table

**Description:** 

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| table_name | path | name of table to be created | Yes | string |
| where | formData | key_value_pairs where key is the column and value is the corresponding value | No | string |
| token | formData | The token that is generated from the IAM token management system. | Yes | string |
| outputFormat | formData | The mode of sending a response. ( defaults to json ) | No | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | The table data is deleted |
| 401 | Unauthorized access to Database |
| 500 | The error reported in deleting data in a table |

### /drop/{table_name}
---
##### ***DELETE***
**Summary:** Update given record(s) in the table

**Description:** 

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| table_name | path | name of table to be created | Yes | string |
| token | formData | The token that is generated from the IAM token management system. | Yes | string |
| outputFormat | formData | The mode of sending a response. ( defaults to json ) | No | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | The table is deleted |
| 401 | Unauthorized access to Database |
| 500 | The error reported in deleting a table |
