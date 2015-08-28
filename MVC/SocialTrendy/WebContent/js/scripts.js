function get_registration_form() {
	        document.getElementById("logForm").style.display = "none";
            document.getElementById("regForm").style.display = "block";
			}
			
function get_login_form() {
	        document.getElementById("regForm").style.display = "none";
	        document.getElementById("logForm").style.display = "block";      
			}
			
function submitForm(formId) {
	var outForm = document.getElementById(formId);
	outForm.submit();
}

function submitRecoveryForm() {
	var outForm = document.getElementById("recovery_users");
	outForm.submit();
}

function setActionType(actionType) {
	var actionTypeHidden = document.getElementById("hdn_action_type_id");
	actionTypeHidden.value = actionType;
}

function setActionType2form(actionType) {
	var actionTypeHidden = document.getElementById("hdn_action_type_id2");
	actionTypeHidden.value = actionType;
}

function setChangeUser(userId) {
	var removedProductHidden = document.getElementById("hdn_removed_product_id");
	removedProductHidden.value = userId;
}

function setChangeUser2form(userId) {
	var removedProductHidden = document.getElementById("hdn_removed_product_id2");
	removedProductHidden.value = userId;
}

function submitRemoveAction(userId) {
	setActionType("remove_action");
	setChangeUser(userId);
	submitForm("add_news");
}

function submitFriendAction(userId) {
	setActionType2form("make_a_friend");
	setChangeUser2form(userId);
	submitForm("edit_users");
}
 
function submitRemoveUser(userId) {
	setActionType2form("remove_user");
	setChangeUser2form(userId);
	submitForm("edit_users");
}

function setChangeUserRec(userId) {
	var removedProductHidden = document.getElementById("hdn_removed_product_id_rec");
	removedProductHidden.value = userId;
}

function setActionTypeRec(actionType) {
	var actionTypeHidden = document.getElementById("hdn_action_type_id_rec");
	actionTypeHidden.value = actionType;
}

function submitMakeAdminActionRec(userId) {
	setActionTypeRec("make_admin");
	setChangeUserRec(userId);
	submitRecoveryForm();
}

function submitFriendActionRec(userId) {
	setActionTypeRec("make_a_friend");
	setChangeUserRec(userId);
	submitRecoveryForm();
}

function submitRecoveryAction(userId) {
	setActionTypeRec("recovery_action");
	setChangeUserRec(userId);
	submitRecoveryForm();
}

function submitMakeAdminAction(userId) {
	setActionType2form("make_admin");
	setChangeUser2form(userId);
	submitForm("edit_users");
}
function show_all_inc_msgs() {
	document.getElementById("all_inc_msgs").style.display = "block";
    document.getElementById("usefull_inc_msgs").style.display = "none";
    document.getElementById("show_all_inc_link").style.display = "none";
}
function show_all_out_msgs() {
	document.getElementById("all_out_msgs").style.display = "block";
    document.getElementById("usefull_out_msgs").style.display = "none";
    document.getElementById("show_all_out_link").style.display = "none";
}