from locust import HttpUser, task, between

class LoadTestUser(HttpUser):

    def on_start(self):
        self.client.post("/auth/login", json={"email": "talhettialvaro@gmail.com", "password": "123456789"})

    @task
    def test_create_user(self):
        self.client.post(
            "", 
            json={
                
            })