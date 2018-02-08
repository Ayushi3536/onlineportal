$(function(){
	
	switch(menu){
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Your Product':
		$('#listproduct').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
		
		default :
			$('#home').addClass('active');
		break;
	}
});