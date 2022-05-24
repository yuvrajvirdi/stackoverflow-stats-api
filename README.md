# stackoverflow-stats-api
REST API for Stack Overflow stats

# Use

### You need a valid Stack Overflow user ID in order to get a response from the API

Hit the following endpoint with your user ID: `https://stackoverflow-stats-api.herokuapp.com/stats?userId=<USER-ID>`

# Example

Response from the following endpoint: `https://stackoverflow-stats-api.herokuapp.com/stats?userId=16142497`

```java
{     
  "status":"200",
  "message":"retrieved",
  "userId":16142497, 
  "username":"yvirdi",
  "reputation":1,
  "reached":628,
  "answers":0,
  "questions":5,
  "percentile":"average engagement",
  "badges":0
}
```

# Installation

Clone the repo

```bash
git clone https://github.com/yuvrajvirdi/stackoverflow-stats-api.git
```

# Running locally

Navigate into the directory and run the following command:

```bash
./mvnw spring-boot:run
```
