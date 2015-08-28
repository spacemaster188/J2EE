<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="4; url=login.jsp">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Social Trendy | We're so sorry</title>
<link rel="shortcut icon" href="favicon.png" />
  <script type='text/javascript' src='jquery-1.7.1.js'></script>
  <script type='text/javascript' src='jquery-ui.min.js'></script>
  <script type='text/javascript' src='blur.min.js'></script>
 
  <style type='text/css'>
      body{
        background-image:url('bg2.jpg');
        background-position:top left;
        background-repeat:repeat;
        
            }
        body{margin:0; padding:0;padding-top:30px;font-family:arial;color:#333;}.target,.targetCopy{ margin:0 auto; width:500px; margin-bottom:50px;  padding:10px;  text-shadow:0 1px 0 white; -moz-border-radius:10px;-webkit-border-radius:10px;border-radius:10px;-moz-box-shadow:0 1px 0 rgba(0,0,0,0.3),0 -1px 0 rgba(255,255,255,0.8),0 4px 10px rgba(0,0,0,0.6);-webkit-box-shadow:0 1px 0 rgba(0,0,0,0.3),0 -1px 0 rgba(255,255,255,0.8),0 4px 10px rgba(0,0,0,0.6);box-shadow:0 1px 0 rgba(0,0,0,0.3),0 -1px 0 rgba(255,255,255,0.8),0 4px 10px rgba(0,0,0,0.6)}  .target p{ text-align:center;font-size:40px}  header,h1{ width:500px;margin:0 auto;color:white;text-shadow:0 2px 2px black;font-size:12em;margin-bottom:25px;font-weight:bold;text-align:center}h1{font-size:12em} .code,.changelog{ font-family:monospace;position:relative;padding:10px;white-space:pre;-moz-border-radius:10px;-webkit-border-radius:10px;border-radius:10px;background-color:rgba(255,255,255,0.3);-moz-box-shadow:0 -1px 0 rgba(0,0,0,0.4),0 2px 5px rgba(0,0,0,0.4) inset,0 1px 0 #fff;-webkit-box-shadow:0 -1px 0 rgba(0,0,0,0.4),0 2px 5px rgba(0,0,0,0.4) inset,0 1px 0 #fff;box-shadow:0 -1px 0 rgba(0,0,0,0.4),0 2px 5px rgba(0,0,0,0.4) inset,0 1px 0 #fff;overflow-x:scroll} .changelog{ white-space:nowrap} h2{ margin:0;padding:0;margin-bottom:7px;color:white;text-shadow:0 -1px 4px rgba(0,0,0,0.2),0 2px 2px black;display:inline-block}  .note{ font-size:40px; margin:0; padding:0;position: relative;top: -3px} button{ float:right; display:inline-block}  .examples{ text-align:center} .examples button{ float:none}  .string{color:#196161} .int{color:#8B0000} .boolean{color:#800080} .comments{color:#666;margin-left:10px} .indent{ text-indent:17px} .arial{ font-family:arial}
    
    a, a:visited{
    	-webkit-transition: all 0.3s ease-out;
     	-moz-transition: all 0.3s ease-out;
      	-ms-transition: all 0.3s ease-out;
       	-o-transition: all 0.3s ease-out;
        transition: all 0.3s ease-out;
    	color:#000;
    }
    
    a:hover{
    	color:transparent;
    	text-shadow:0 0 5px #000;
    }
    
    a:active{
    	position:relative;
    	top:1px;
    }
    
    button{
    	min-width: 48px;
		height: 18px;
		background: rgba(255, 255, 255, 0.5);
		border-radius: 3px;
		border: none;
		-moz-box-shadow: 0 -1px 0 #fff, 0 1px 2px rgba(0,0,0,0.5);
		-webkit-box-shadow: 0 -1px 0 #fff, 0 1px 2px rgba(0,0,0,0.5);
		box-shadow: 0 -1px 0 #fff, 0 1px 2px rgba(0,0,0,0.5);
		cursor:pointer;
		color:black;
		text-shadow:inherit;
		
		font-family: 'Lucida Grande';
		font-size: 40px;
		text-decoration:none;
		display:inline-block;
    }
    
    button:hover{
    	background: rgba(255, 255, 255, 0.7);
    	color:#333;
    }
    
    button:active{
    
		-moz-box-shadow: 0 -1px 0 #fff, 0 0 2px rgba(0,0,0,0.5);
		-webkit-box-shadow: 0 -1px 0 #fff, 0 0 2px rgba(0,0,0,0.5);
		box-shadow: 0 -1px 0 #fff, 0 0 2px rgba(0,0,0,0.5);
    
    	position:relative;
    	top:1px;
    }
    
    
    .examples button{
    	margin-bottom:10px;
    }
    #download button{
        float:none;
        margin-bottom:10px;
    }
    
    
    #wideContainer{
    	width: 100%;
		background-image: url('bgs/2.png');
		background-position: top left;
		background-repeat: repeat;
		padding-top: 30px;
		height: 165px;
		margin-bottom: 50px;
		-moz-box-shadow: 0 2px 5px rgba(0,0,0,0.5) inset,0 -1px 0 #777, 0 1px 0 #fff;
    	-webkit-box-shadow: 0 2px 5px rgba(0,0,0,0.5) inset,0 -1px 0 #777, 0 1px 0 #fff;
    	box-shadow: 0 2px 5px rgba(0,0,0,0.5) inset,0 -1px 0 #777, 0 1px 0 #fff;
    }
  </style>
  
<script type='text/javascript'>


$(document).ready(function(){
    
    $('.target').blurjs({
		source: 'body',
        radius: 7,
        overlay: 'rgba(255,255,255,0.4)',
        optClass: 'blurred',
        cache:false
    });
    
    
    // Demo Buttons
    $('button').live('click', function(e){e.preventDefault();if($(this).attr('rel') && !$(this).hasClass('dl')){window.location = "?bg="+$(this).attr('rel');} else if($(this).text()=="Demo") {demos($(this).parent().attr('id'));} else if($(this).hasClass('dl')){
    
    track($(this).attr('rel'), $(this).attr('rel'));
    
    }  
    });
    
});


function demos(object){if(object=="highblur"){$('#'+object).blurjs({source:'body',radius:10})}else if(object=="overlayblur"){$('#'+object).blurjs({source:'body',overlay:'rgba(255,255,255,0.4)'})}else if(object=="overlayblur2"){$('#'+object).blurjs({source:'body',overlay:'rgba(0,100,100,0.1)'})}else if(object=="offsetblur"){$('#'+object).blurjs({source:'body',offset:{x:15,y:-12}})}else if(object=="sourceblur"){$('#'+object).blurjs({source:'#wideContainer',overlay:'rgba(255,255,255,0.3)'});} else if(object=="draggable"){$('#'+object).blurjs({draggable:true,overlay:'rgba(255,255,255,0.4)'});} else if(object=="cacheblur"){$('#'+object).blurjs({cache:true,debug:true});} }

</script>
<script type="text/javascript" src="functions.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="screen.css" />
    <script type="text/javascript" src="jquery.js"></script>
    <script type="text/javascript" src="cmxform.js"></script>

</head>
<body background="bg2.jpg">
<br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br>
<div class="targetCopy" style="cursor:pointer;" align="center">
	        <br>
<strong>WE'RE SORRY,  BUT YOUR ACCOUNT INACTIVE <br><br>  
	        IT CAN BE DELETED OR MOOVED<br><br>             
	        BUT YOU CAN <a href="mailto:limitvadim@ya.ru">EMAIL US</a> TO RESTORE IT<br>
<a href="logout.do">BACK TO MAINPAGE</a><br><br><br>
			  <script type="text/javascript" src="code.js" data-color="#ddd" id="codejs"></script>
  		</div>
</body>
</html>