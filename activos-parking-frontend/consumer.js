async function getAutoType() {
    try {
        const response = await fetch('http://localhost:8080/typeAutomovil');
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error:', error);
        throw error; // Puedes manejar el error o lanzarlo nuevamente según tus necesidades
    }
}


async function parking() {
    try {
        placa = document.getElementById('placa').value;

        const regexPlaca = /^[A-Z0-9]{6}$/;

        if (placa.length === 0 || !regexPlaca.test(placa)) {
            alert('Ingrese una placa válida');
            return;
        }

        const data = {
            "placa": placa,
            "typeAutomovil": {
                "id": document.getElementById('autoType').value
            }
        };

        const response = await fetch('http://localhost:8080/automovil/ingreso', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Ingreso exitoso");

        const result = await response.json();
        return result;
    } catch (error) {
        if(error.message.includes('Ya existe un registro con la misma placa sin fecha de salida')) {
        alert('Ya existe un carro con la misma placa sin fecha de salida, por favor salga del parqueadero antes de ingresar nuevamente');
        }
        throw error; // Puedes manejar el error o lanzarlo nuevamente según tus necesidades
    }
}

async function exitParking() {
    try {
        placa = document.getElementById('placa').value;

        const regexPlaca = /^[A-Z0-9]{6}$/;

        if (placa.length === 0 || !regexPlaca.test(placa)) {
            alert('Ingrese una placa válida');
            return;
        }

        const data = {
            "id":0,
            "placa": placa,
            "typeAutomovil": {
                "id": document.getElementById('autoType').value
            }
        };

        const response = await fetch('http://localhost:8080/automovil/salida', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert(await response.text());

    } catch (error) {
        alert(error.message)
        throw error; // Puedes manejar el error o lanzarlo nuevamente según tus necesidades
    }
}



window.onload = async function() {
    try {
        const autoTypeData = await getAutoType();
        
        const autoTypeElement = document.getElementById('autoType');

        if (autoTypeData) {
            autoTypeElement.innerHTML = autoTypeData.map(type => `<option value="${type.id}">${type.type}</option>`).join('');
        }

    } catch (error) {
        console.error('Error:', error);
    }
};
