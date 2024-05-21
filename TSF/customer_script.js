const bank_customers = [
  {
    accountNo: 1001,
    name: "Alice Smith",
    email: "alice@email.com",
    currentBalance: 1000,
  },
  {
    accountNo: 1002,
    name: "Bob Johnson",
    email: "bob@email.com",
    currentBalance: 500,
  },
  {
    accountNo: 1003,
    name: "Charlie Brown",
    email: "charlie@email.com",
    currentBalance: 2000,
  },
  {
    accountNo: 1004,
    name: "M S Dhoni",
    email: "msd@email.com",
    currentBalance: 20000,
  },
  {
    accountNo: 1005,
    name: "Hiramal Chaansi",
    email: "chaansihira78@email.com",
    currentBalance: 29100,
  },
];

const customerTable = document.getElementById("customer-table");
const tableBody = customerTable.querySelector("tbody");

function createTable() {
  fetch("http://localhost:8080/customers")
    .then((response) => response.json())
    .then((data) => {
      // Handle the data here (e.g., update UI, process results)
      data.forEach((data) => {
        const tableRow = document.createElement("tr");

        const accountNoCell = document.createElement("td");
        accountNoCell.textContent = data.customerId;
        tableRow.appendChild(accountNoCell);

        const nameCell = document.createElement("td");
        nameCell.textContent = data.name;
        tableRow.appendChild(nameCell);

        const emailCell = document.createElement("td");
        emailCell.textContent = data.email;
        tableRow.appendChild(emailCell);

        const balanceCell = document.createElement("td");
        balanceCell.textContent = `Rs.${data.balance}`; // Add currency symbol
        tableRow.appendChild(balanceCell);
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
