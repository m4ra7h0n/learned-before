<html>
<head>
<script type="text/javascript">
	function check(form) {
		with (form) {
			if (name.value == "") {
				alert("empty name");
				return false;
			}
			if (job.value == "") {
				alert("empty job");
				return false;
			}
		}
	}
</script>
</head>
<body>
	<h2>hello world!</h2>
	
	<form action="/test/AddServlet" method="POST"
		onsubmit="return check(this)">
		<table border=1 style="background-color: Black; color: Black">
			<thead style="height: 36px; ">
				
			</thead>
			<tr bgcolor="lightgrey">
				<td colspan="2" style="height: 13px; ">add user info</td>
			</tr>
			<tr style="height: 30px; ">
				<td style="height: 22px; width: 154px; color: Black"><p align="center">name</p></td>
				<td style="color: Blue; height: 63px; width: 182px"><p align="center"><big><b>job</b></big></p></td>
			</tr>
			<tr>
				<td><input type="text" name="name"></td>
				<td><input type="text" name="job"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="click"></td>
			</tr>
		</table>

	</form>
</body>
</html>
