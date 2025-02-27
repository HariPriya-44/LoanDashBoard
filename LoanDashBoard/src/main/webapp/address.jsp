<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Communication Address</title>
<style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            background-color: #f4f4f4;
        }
        
        .form-container {
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: white;
            width: 400px;
            min-height:100vh;
        }
        
        .back-btn {
            text-decoration: none;
            font-size: 14px;
            color: black;
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        
        .back-btn:hover {
            color: darkgray;
        }
        
        h1, p {
            text-align: left;
            margin: 5px 0;
        }
        
        h1 {
            font-size: 14px;
        }
        
        h4 {
            font-size: 12px;
        }
        
        p {
            font-size: 12px;
            color: #555;
            margin-bottom: 20px;
        }
        
        button {
            padding: 8px 40px;
            margin-top: 20px;
            min-width: 10vw;
            background-color: blue;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        
        button:hover {
            background-color: darkblue;
        }
        
        .required {
            color: red;
            font-weight: bold;
        }

        .hidden {
            display: none;
        }
        
        .CommunicationAddress{
         display:flex;
        }
        
        
</style>
</head>
<body>
<div class="form-container">
    <a href="maritalstatus.html" class="back-btn">← Back</a> 
    <h1>Communication Address</h1>

    <form action="Address" method="post">
        <div class = "CommunicationAddress">
          <div>
             <label for="address1"><h4>Address Line 1<span class="required">*</span></h4></label>
             <input type="text" id="address1" name="address1" placeholder="Address" required>

            <label for="address2"><h4>Address Line 2</h4></label>
            <input type="text" id="address2" name="address2" placeholder="Address">

            <label for="pincode"><h4>Pincode<span class="required">*</span></h4></label>
            <input type="text" id="pincode" name="pincode" placeholder="Pincode" required pattern="[0-9]{6}" maxlength="6">
            
            <label for="city"><h4>City<span class="required">*</span></h4></label>
            <input type="text" id="city" name="city" placeholder="City" required>

            <label for="state"><h4>State<span class="required">*</span></h4></label>
            <input type="text" id="state" name="state" placeholder="State" required>

            <label for="nature"><h4>Nature of Address</h4></label>
            <select id="nature" name="nature" >
                <option value="" disabled selected>Please select</option>
                <option value="Self-Owned">Self-Owned</option>
                <option value="With Parents">With Parents</option>
                <option value="With Relatives">With Relatives</option>
                <option value="With Friends">With Friends</option>
                <option value="Rented">Rented</option>
                <option value="Paying Guest">Paying Guest</option>
                <option value="Lease">Lease</option>
                <option value="Pagadi">Pagadi</option>
            </select>
            
            <label for="years"><h4>Years at Current Address</h4></label>
            <input type="number" id="years" name="years" placeholder="Enter years" >
                 
            <div class="checkbox-container">
                <input type="checkbox" id="permanent" name="permanent" onclick="togglePermanentAddress()">
                <label for="permanent">Use this address as permanent address</label>
            </div>
      </div>

            
      <div>
      <div id="permanentAddressSection" >
                <h1>Permanent Address</h1>

                <label for="permanent_address1"><h4>Address Line 1<span class="required">*</span></h4></label>
                <input type="text" id="permanent_address1" name="permanent_address1" placeholder="Address">

                <label for="permanent_address2"><h4>Address Line 2</h4></label>
                <input type="text" id="permanent_address2" name="permanent_address2" placeholder="Address">

                <label for="permanent_pincode"><h4>Pincode<span class="required">*</span></h4></label>
                <input type="text" id="permanent_pincode" name="permanent_pincode" placeholder="Pincode" pattern="[0-9]{6}" maxlength="6">

                <label for="permanent_city"><h4>City<span class="required">*</span></h4></label>
                <input type="text" id="permanent_city" name="permanent_city" placeholder="City">

                <label for="permanent_state"><h4>State<span class="required">*</span></h4></label>
                <input type="text" id="permanent_state" name="permanent_state" placeholder="State">
            </div>
      </div>

        </div>
        <div class="submitBtn">
                <button type="submit">Continue</button>
            </div>
    </form>
            
</div>

<script>
    function togglePermanentAddress() {
        let checkbox = document.getElementById("permanent");
        let permanentAddressSection = document.getElementById("permanentAddressSection");

        if (!checkbox.checked) {
            permanentAddressSection.classList.toggle("hidden");

            document.getElementById("permanent_address1").setAttribute("required", "true");
            document.getElementById("permanent_pincode").setAttribute("required", "true");
            document.getElementById("permanent_city").setAttribute("required", "true");
            document.getElementById("permanent_state").setAttribute("required", "true");
        } else {
            permanentAddressSection.classList.toggle("hidden");

            document.getElementById("permanent_address1").removeAttribute("required");
            document.getElementById("permanent_pincode").removeAttribute("required");
            document.getElementById("permanent_city").removeAttribute("required");
            document.getElementById("permanent_state").removeAttribute("required");
        }
    }
</script>
</body>
</html>