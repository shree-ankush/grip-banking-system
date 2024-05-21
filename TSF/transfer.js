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

const transferForm = document.getElementById("transfer-form");
const transferMessage = document.getElementById("transfer-message");

let globalData; // Declare a global variable

function createSelectOptions() {
  const senderSelect = document.getElementById("sender-select");
  const receiverSelect = document.getElementById("receiver-select");

  fetch("http://localhost:8080/customers")
    .then((response) => response.json())
    .then((data) => {
      globalData = data;

      const senderOption = document.createElement("option");
      senderOption.value = "0";
      senderOption.text = "select";
      senderOption.disabled = true;
      senderOption.defaultSelected = true;
      senderSelect.appendChild(senderOption);

      data.forEach((customer) => {
        const senderOption = document.createElement("option");
        senderOption.value = customer.email;
        senderOption.text = customer.email;
        senderSelect.appendChild(senderOption);
      });
      senderSelect.addEventListener("change", () => {
        receiverSelect.innerHTML = ""; // Clear existing options
        const selectedSenderEmail = senderSelect.value;
        console.log(selectedSenderEmail);
        const receiverOption = document.createElement("option");
        receiverOption.value = "0";
        receiverOption.textContent = "select";
        receiverOption.disabled = true;
        receiverOption.defaultSelected = true;
        receiverSelect.appendChild(receiverOption);
        data.forEach((customer) => {
          if (customer.email !== selectedSenderEmail) {
            const receiverOption = document.createElement("option");
            receiverOption.value = customer.email;
            receiverOption.textContent = customer.email;
            receiverSelect.appendChild(receiverOption);
          }
        });
      });
      // Assign the fetched data to the global variable
      // Handle the data here (e.g., update UI, process results)
      console.log(globalData);
    })
    .catch((error) => {
      // Handle any errors
      console.error("Error fetching data:", error);
    });

  // Exclude the selected sender from receiver options
}

createSelectOptions();

function handleTransfer(event) {
  event.preventDefault();

  let transferBody = {
    senderEmail: "",
    receiverEmail: "",
    transferAmount: "",
  };

  transferBody.senderEmail = document.getElementById("sender-select").value;
  console.log(transferBody.senderEmail);
  transferBody.receiverEmail = document.getElementById("receiver-select").value;
  transferBody.transferAmount = parseFloat(
    document.getElementById("amount").value
  );

  var url = "http://localhost:8080/transfer";

  fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(transferBody),
  })
    .then((response) => response.json())
    .then((data) => {
      // Handle the data here (e.g., update UI, process results)
      console.log(data);
      document.getElementById("transfer-message").innerHTML = data.status.message;
    })
    .catch((error) => {
      // Handle any errors
      console.error("Error sending POST request:", error);
    });

  console.log(
    `sender : ${transferBody.senderEmail} reciever : ${transferBody.receiverEmail} balance : ${transferBody.transferAmount}`
  );

  transferForm.reset(); // Clear form fields after successful transfer
}

transferForm.addEventListener("submit", handleTransfer);
