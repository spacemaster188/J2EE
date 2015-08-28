	function get_login_form() {
				document.getElementById("authorization_form").style.display = "block";
                document.getElementById("registration_form").style.display = "none";
			}
			
			function get_registration_form() {
				document.getElementById("authorization_form").style.display = "none";
                document.getElementById("registration_form").style.display = "block";
			}
			
			function get_recycled_form() {
				document.getElementById("recycledForm").style.display = "block";
			}
			
			function get_fixed_form() {
            document.getElementById("fixedForm").style.display = "block";
			}
			
			function get_todayAdd_form() {
				document.getElementById("todayForm").style.display = "block";
			}
			
			function get_tomorrowAdd_form() {
				document.getElementById("tomorrowForm").style.display = "block";
			}
				
			function get_somedayAdd_form() {
				document.getElementById("somedayForm").style.display = "block";
			}
			
			function uploadFileAction(idTask) {
				document.getElementById("upload_form").style.display = "block";
				var taskId = document.getElementById("upload_taskId_id");
	            taskId.value = idTask;
			}
			
			function fixedRemoveAction(taskId) {
			setServletAction("recycle.do");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function fixedRemoveFileAction(taskId) {
			setServletAction("deleteFile.do");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function recycleRemoveAction(taskId) {
			setServletAction("recycle.do");
			setList("recycled");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function recycleRestoreAction(taskId) {
			setServletAction("restore.do");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function recycleRemoveFileAction(taskId) {
			setServletAction("deleteFile.do");
		    setTaskId(taskId);
	        submitForm();
            }
			
			function todayRemoveAction(taskId) {
			setServletAction("recycle.do");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function todayFixAction(taskId) {
			setServletAction("fix.do");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function RemoveFileAction(idTask) {
			setServletAction("deletefile.do");
	        setTaskId(idTask);
	        submitForm();
            }
			
			function todayRemoveFileAction(taskId) {
			setServletAction("deleteFile.do");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function tomorrowRemoveAction(taskId) {
			setServletAction("recycle.do");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function tomorrowFixAction(taskId) {
			setServletAction("fix.do");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function tomorrowRemoveFileAction(taskId) {
			setServletAction("deleteFile.do");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function somedayRemoveAction(taskId) {
			setServletAction("recycle.do");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function somedayFixAction(taskId) {
			setServletAction("fix.do");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function somedayRemoveFileAction(taskId) {
			setServletAction("deleteFile.do");
	        setTaskId(taskId);
	        submitForm();
            }
			
			function submitForm() {
	        var hiddenForm = document.getElementById("hidden");
	        hiddenForm.submit();
            }

            function setList(list) {
	        var actionTypeHidden = document.getElementById("hidden_action_type_id");
	        actionTypeHidden.value = list;
            }
			
			function setAction(action) {
	        var hiddenAction = document.getElementById("hdn_todo_id");
	        hiddenAction.value = action;
            }
			
			function setTaskId(taskId) {
	        var hiddenTaskId = document.getElementById("hdn_taskId_id");
	        hiddenTaskId.value = taskId;
            }
			
			function setFileStr(fileStr) {
	        var filevar = document.getElementById("hdn_todo_id");
	        filevar.value = fileStr;
            }
			
			function setServletAction(servlet) {
	        form1=document.getElementById("hidden");
            form1.action=servlet;
            }