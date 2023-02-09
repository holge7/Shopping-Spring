import requests
import time
import concurrent.futures

url = "http://localhost:8081/api/v1/login"
payload={'email': 'jorge@gmail.com', 'password': 'password'}

# Función que se encarga de realizar la petición
def fetch_url(session, url, payload):
    start = time.time()
    response = session.post(url, data=payload)
    if(response.status_code != 200):
        print("Response fail with status: ", response.status_code)
    end = time.time()
    return response.status_code, response.text, end - start

# Crea una sesión compartida para las peticiones
session = requests.Session()

# Realiza las x peticiones de manera paralela
with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
    start_time = time.time()
    futures = []
    for i in range(1000):
        #print("Generating request ",i)
        future = executor.submit(fetch_url, session, url, payload)
        #futures.append(future)

    #for future in concurrent.futures.as_completed(future_to_url):
    #    status, response, elapsed = future.result()
    #    print(f"Status code: {status}")
    #    print(f"Response text: {response}")
    #    print(f"Elapsed time: {elapsed}")

end_time = time.time()
total_time = end_time - start_time
print("Total time for 10 requests:", total_time, "seconds")

# Cierra la sesión
session.close()
