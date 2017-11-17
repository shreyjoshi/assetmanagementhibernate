
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome SupportStaff</title>
<style>
body{
background-color:#EEEEEE;
}


.top_bar{
	background:black;
	padding:5px 5px;}
.social_icons{
	margin-left:1155px;
	margin-top:-1000px;
	margin-bottom:1000px;
}
.contact_info{color:#fff; font-size:16px;}
.upper{
		margin-top:-250px;
		margin-left:300px;
}
.ram{
		background-color: #fff;
	border-top: 2px dashed #8c8b8b;
	}
	
	
	 ul {
        display:block;
        background:#45619D;
        list-style:none;
        margin:0;
        padding:12px 10px;
        /* height:21px; */
    }
    ul li {
        float:left;
        font:13px helvetica;
        font-weight:bold;
        margin:12px -5px;
    }
    ul li a {
        color:#FFF;
        text-decoration:none;
        padding:6px 5px;
        cursor:pointer;
    }
    ul li a:hover {
        background:#425B90;
        text-decoration:none;
        cursor:pointer;
    }
        
    #noti_Container {
        position:relative;
        margin-left:880px;
        margin-top:-15px;
    }
       
    /* A CIRCLE LIKE BUTTON IN THE TOP MENU. */
    #noti_Button {
        width:18px;
        height:18px;
        line-height:18px;
        border-radius:50%;
        -moz-border-radius:50%; 
        -webkit-border-radius:50%;
        background:#FFF;
        margin:-18px 28px -22px 200px;
        cursor:pointer;
    }
        
    /* THE POPULAR RED NOTIFICATIONS COUNTER. */
    #noti_Counter {
        display:block;
        position:absolute;
        background:#E1141E;
        color:#FFF;
        font-size:12px;
        font-weight:normal;
        padding:0px 3px;
        margin:-5px 0 0 210px;
        border-radius:2px;
        -moz-border-radius:2px; 
        -webkit-border-radius:2px;
        z-index:1;
    }
        
    /* THE NOTIFICAIONS WINDOW. THIS REMAINS HIDDEN WHEN THE PAGE LOADS. */
    #notifications {
        display:none;
        width:300px;
        position:absolute;
        top:50px;
        left:0;
        background:#FFF;
        border:solid 1px rgba(100, 100, 100, .20);
        -webkit-box-shadow:0 3px 8px rgba(0, 0, 0, .20);
        z-index: 0;
    }
    /* AN ARROW LIKE STRUCTURE JUST OVER THE NOTIFICATIONS WINDOW */
    #notifications:before {         
        content: '';
        display:block;
        width:0;
        height:0;
        color:transparent;
        border:10px solid #CCC;
        border-color:transparent transparent #FFF;
        margin-top:-10px;
        margin-left:10px;
    }
        
    h6 {
        display:block;
        color:#333; 
        background:#FFF;
        font-weight:bold;
        font-size:13px;    
        padding:8px;
        margin:0;
        border-bottom:solid 1px rgba(100, 100, 100, .30);
    }
        
    .seeAll {
        background:#F6F7F8;
        padding:8px;
        font-size:12px;
        font-weight:bold;
        border-top:solid 1px rgba(100, 100, 100, .30);
        text-align:center;
    }
    .seeAll a {
        color:#3b5998;
    }
    .seeAll a:hover {
        background:#F6F7F8;
        color:#3b5998;
        text-decoration:underline;
    }
	
	
	
</style>

</head>

<script type="text/javascript">

$(document).ready(function () {
	
    // ANIMATEDLY DISPLAY THE NOTIFICATION COUNTER.
    $('#noti_Counter')
        .css({ opacity: 0 })
        .text('7')              // ADD DYNAMIC VALUE (YOU CAN EXTRACT DATA FROM DATABASE OR XML).
        .css({ top: '-10px' })
        .animate({ top: '-2px', opacity: 1 }, 500);

    $('#noti_Button').click(function () {

        // TOGGLE (SHOW OR HIDE) NOTIFICATION WINDOW.
        $('#notifications').fadeToggle('fast', 'linear', function () {
            if ($('#notifications').is(':hidden')) {
                $('#noti_Button').css('background-color', '#2E467C');
            }
            else $('#noti_Button').css('background-color', '#FFF');        // CHANGE BACKGROUND COLOR OF THE BUTTON.
        });

        $('#noti_Counter').fadeOut('slow');                 // HIDE THE COUNTER.

        return false;
    });

    // HIDE NOTIFICATIONS WHEN CLICKED ANYWHERE ON THE PAGE.
    $(document).click(function () {
        $('#notifications').hide();

        // CHECK IF NOTIFICATION COUNTER IS HIDDEN.
        if ($('#noti_Counter').is(':hidden')) {
            // CHANGE BACKGROUND COLOR OF THE BUTTON.
            $('#noti_Button').css('background-color', '#2E467C');
        }
    });

    $('#notifications').click(function () {
        return false;       // DO NOTHING WHEN CONTAINER IS CLICKED.
    });
});
</script>
<body>

		<!----Header---->
	<div class="container-fluid top_bar">
         <div class="container">
			<div class="row">
			<div class="col-sm-12">
			<font color=white><a href="supportstaff"><i class="" style="font-size:17px; color:#fff;">Asset Support Staff </i></a></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-sign-out" style="font-size:17px; color:#fff;">welcome ${username}</i>&nbsp;&nbsp;
			 <li id="noti_Container">
                <div id="noti_Counter"></div>   <!--SHOW NOTIFICATIONS COUNT.-->
                
                <!--A CIRCLE LIKE BUTTON TO DISPLAY NOTIFICATION DROPDOWN.-->
                <div id="noti_Button"></div>    

                <!--THE NOTIFICAIONS DROPDOWN BOX.-->
                <div id="notifications">
                    <h3>Notifications</h3>
                    <div style="height:100px;"></div>
                    <div class="seeAll"><a href="#">See All</a></div>
                </div>
            </li>
            <span class="social_icons" align="right"><font color=white><a href="logout"><i class="fa fa-sign-out" style="font-size:17px; color:#fff;">Support Staff Logout</i></a></font></span>
			
			</div>
			
			
			</div>
			</div>
			</div>
			


<h2><font color="green">${changepwdmsg}</font></h2>
			
			<br/>
			<div class="container">
			<div class="row">
			<div class="col-sm-12">
		
			<h2><font color="green">${changepwdmsg}</font></h2>
			<h3><a href="ViewSupportProfile">My Profile</a></h3>
			<h3><a href="ViewResourceRequestSupport">View Resource request</a></h3>
			<h3><a href="ViewTransferredHistory">View Transferred assets</a></h3>
			<h3><a href="ViewReportSupport">view history of resources</a></h3>
			<h3><a href="changeEmployeePassword">change password</a></h3>
			<h3><a href="ViewRejectedRequests">Rejected Requests</a></h3>
			<hr width="3" class="upper" size="240">
			</div>
			</div>
			</div>
			

<hr class="ram">
<center><footer><font color=green><h3>&copy; Copyright ® 2017 AssetManagement.in All rights reserved.
Developed and Powered by R.K. & Team</h3></font></footer></center>

</body>
</html>