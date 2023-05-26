<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Login Form</title>
  </head>
  <body>
    <form action="process_form.jsp" method="post">
      <table style="background-color: lightgrey; margin: auto">
        <tr>
          <td>Enter First Name</td>
          <td><input type="text" name="fn" /></td>
        </tr>
        <tr>
          <td>Enter Last name</td>
          <td><input type="text" name="ln" /></td>
        </tr>
        <tr>
          <td>Test Score</td>
          <td><input type="number" name="score" /></td>
        </tr>
        <tr>
          <td>Course</td>
          <td><input type="text" name="course" /></td>
        </tr>
        <tr>
          <td>DoB</td>
          <td><input type="date" name="dob" /></td>
        </tr>

        <tr>
          <td><input type="submit" value="Student Admission" /></td>
        </tr>
      </table>
    </form>
  </body>
</html>
