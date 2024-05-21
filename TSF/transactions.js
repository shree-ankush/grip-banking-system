const customerTable = document.getElementById("transaction-table");
const tableBody = customerTable.querySelector("tbody");

function createTable() {
  fetch("http://localhost:8080/get-transactions")
    .then((response) => response.json())
    .then((data) => {
      // Handle the data here (e.g., update UI, process results)
      data.forEach((data) => {
        const tableRow = document.createElement("tr");

        const accountNoCell = document.createElement("td");
        accountNoCell.textContent = data.transactionId;
        tableRow.appendChild(accountNoCell);

        const nameCell = document.createElement("td");
        nameCell.textContent = data.senderEmail;
        tableRow.appendChild(nameCell);

        const emailCell = document.createElement("td");
        emailCell.textContent = data.recieverEmail;
        tableRow.appendChild(emailCell);

        const timestampCell = document.createElement("td");
        timestampCell.textContent = data.timestamp;

        const balanceCell = document.createElement("td");
        balanceCell.textContent = `Rs.${data.transactionAmount}`; // Add currency symbol
        tableRow.appendChild(balanceCell);
        tableRow.appendChild(timestampCell);
        tableBody.appendChild(tableRow);
      });
      console.log(data);
    })
    .catch((error) => {
      // Handle any errors
      console.error("Error fetching data:", error);
    });
}

createTable();
