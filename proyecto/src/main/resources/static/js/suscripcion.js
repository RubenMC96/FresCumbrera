document.getElementById("enviar").addEventListener("click", createSubscription, false);



const nombre = document.getElementById("nombre");
const elementValue = nombre.value;

async function createSubscription() {
    const subscriptionData = {
      nombre: nombre.value
    };
  
    const response = await fetch('http://localhost:9000/api/suscripciones', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(subscriptionData)
    });
  
    if (response.ok) {
      const responseData = await response.json();
      console.log('Subscription created successfully:', responseData);
    } else {
      console.error('Failed to create subscription:', response.status, response.statusText);
    }
  }
  





