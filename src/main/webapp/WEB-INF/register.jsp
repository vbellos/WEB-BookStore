<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="scripts.jsp"%>
<head>
<title>Register</title>
</head>
<body>
<%@ include file="../navbar.jsp" %>
<br>
<br>
 <div align="center">
 
		<div class="container">
			<h3 class="text-center">Register Form</h3>
			<hr>
			<!-- <div class="container text-center"> -->
            <div class="container">
		</div>	
  <form action="<%= request.getContextPath() %>/Register" method="post" class="form-horizontal" content="text/html;charset=UTF-8" >
      <fieldset>


          <!-- Text input-->
          <div class="control-group">
              <label class="control-label" for="username">Username:</label>
              <div class="controls">
                  <input id="username" name="username" type="text" placeholder="" class="input-xlarge" required="">

              </div>
          </div>

          <!-- Text input-->
          <div class="control-group">
              <label class="control-label" for="fname">First Name:</label>
              <div class="controls">
                  <input id="fname" name="fname" type="text" placeholder="" class="input-xlarge" required="">

              </div>
          </div>

          <!-- Text input-->
          <div class="control-group">
              <label class="control-label" for="lname">Last Name:</label>
              <div class="controls">
                  <input id="lname" name="lname" type="text" placeholder="" class="input-xlarge" required="">

              </div>
          </div>

          <!-- Text input-->
          <div class="control-group">
              <label class="control-label" for="email">Email:</label>
              <div class="controls">
                  <input id="email" name="email" type="text" placeholder="" class="input-xlarge" required="">

              </div>
          </div>

          <!-- Text input-->
          <div class="control-group">
              <label class="control-label" for="phone">Phone:</label>
              <div class="controls">
                  <input id="phone" name="phone" type="text" placeholder="" class="input-xlarge" required="">

              </div>
          </div>

          <!-- Password input-->
          <div class="control-group">
              <label class="control-label" for="password">Password:</label>
              <div class="controls">
                  <input id="password" name="password" type="password" placeholder="" class="input-xlarge" required="">

              </div>
          </div>

          <!-- Select Basic -->
          <div class="control-group">
              <label class="control-label" for="regas">Register As:</label>
              <div class="controls">
                  <select id="regas" name="regas" class="input-xlarge">
                      <option>Client</option>
                      <option>Author</option>
                  </select>
              </div>
          </div>
        <br>
          <!-- Button -->
          <div class="control-group">
              <input type="submit" value="Register" class="btn btn-success" />
              </div>


      </fieldset>
  </form>


 </div>
</body>
</html>