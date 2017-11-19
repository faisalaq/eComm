$(function(){
	$("#addToCart").click(function(){
		$.ajax({
			url: '',
			method: 'post',
			type:'json',
			success : function(cart){
				//css code to show number overlay on shopping cart icon
				if(cart){
					console.log(cart);
				}
				else{
					//shopping cart is empty as user hasnt logged in
					// force them to login or create account
					window.location.href = "/login";
				}
			},
			error: function(){
				console.log("error adding product to cart");
			}
		});
	});
});