 function validateForm() {
    var vlogin = document.forms["register"]["login"].value;
    var vpwd = document.getElementById("password").value;
    if (vlogin.length < 6) {
        alert("Field login should be at least 6 characters length");
        document.forms["register"]["login"].focus();
        return false;
    }
    if (vlogin.length > 10) {
        alert("Field login should be max 10 characters length");
        document.forms["register"]["login"].focus();
        return false;
    }

    if (vpwd.length < 6) {
         alert("Field password should be min 6 characters length");
         document.forms["register"]["password"].focus();
         return false;
    }
}

function validatePwd() {
    var vpwd = document.getElementById("password").value;
    var vrpwd = document.getElementById("passwordRepeat").value;
    if (vpwd != vrpwd) {
        document.getElementById("pwdRepeat").style.display="inline";
    } else {
        document.getElementById("pwdRepeat").style.display="none";
    }

}

 function hideDateErrorMessage() {
     document.getElementById("dateError").style.display="none";
 }
