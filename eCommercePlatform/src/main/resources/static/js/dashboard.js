$(function() {
	$('.navbar-toggle').click(function() {
		$('.navbar-nav').toggleClass('slide-in');
		$('.side-body').toggleClass('body-slide-in');
		$('#search').removeClass('in').addClass('collapse').slideUp(200);

		// / uncomment code for absolute positioning tweek see top comment in
		// css
		// $('.absolute-wrapper').toggleClass('slide-in');

	});

	// Remove menu for searching
	$('#search-trigger').click(function() {
		$('.navbar-nav').removeClass('slide-in');
		$('.side-body').removeClass('body-slide-in');

		// / uncomment code for absolute positioning tweek see top comment in
		// css
		// $('.absolute-wrapper').removeClass('slide-in');

	});

	$("#slider-range").slider({
		range : true,
		min : 0,
		max : 500,
		values : [ 75, 300 ],
		slide : function(event, ui) {
			$("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
		}
	});
	$("#amount").val(
			"$" + $("#slider-range").slider("values", 0) + " - $"
					+ $("#slider-range").slider("values", 1));
	
	$("body").on("click", "button[id*='editButton']", function(){
		var id = $(this).attr("id");
		id = id.split("-")[1];
		
		window.location.href="dashboard/products/" +id;
	});

	$("body").on("click", "button[id*='deleteButton']", function(){
		var confirmBox = confirm("Are you sure you want to delete this product?");
		if(confirmBox == true){
			var id = $(this).attr("id");
			id = id.split("-")[1];
			
			$.post("dashboard/products/" +id +"/delete");			
		}
	});
	
	$("body").on({
		mouseenter: function(){
			$(this).css("background-color", "rgb(228,228,228)");
			$(this).children("button[id*='editButton']").removeClass("hidden");
			$(this).children("button[id*='deleteButton']").removeClass("hidden");
		},
		mouseleave: function(){
			$(this).css("background-color", "rgb(255,255,255)");
			$(this).children("button[id*='editButton']").addClass("hidden");
			$(this).children("button[id*='deleteButton']").addClass("hidden");
		}
	},"div[id*='productCard']");
	
	$("body").on("click", "button[id*='createProduct']", function(){
		var id = getId($(this));
		
		$.ajax({
			url: "dashboard/products",
			method: "post",
			type: "json",
			success: function(product){
				console.log(product);
				$("[id*='-0']").each(function(){
					var elementId = $(this).prop("id");
					elementId = elementId.replace("\-0","-"+product.id);
					$(this).prop("id", elementId);
				});
				
				$("#placehoderCard1-" + product.id).addClass("hidden");
				$("#placehoderCard2-" + product.id).removeClass("hidden");
				
			},
			error: function(){
				console.log("error");
			}
		});
	});
	
	
	$("body").on("click", "button[id*='addImageUrl']", function(){
		var id = getId($(this));
		var imageVal = $("#imageUrl-"+id).val();
		
		$.ajax({
			url: "dashboard/products/"+id,
			method: "post",
			data: { "fieldName": "imageUrl",
				"fieldValue": imageVal},
			type: "json",
			success: function(product){
				console.log(product);
				$("#placehoderCard2-" + product.id).addClass("hidden");
				$("#placehoderCard3-" + product.id).removeClass("hidden");
			},
			error: function(){
				console.log("error");
			}
		});
	});
	
	$("body").on("click", "button[id*='addShortDesc']", function(){
		var id = getId($(this));
		var shortDescVal = $("#shortDesc-"+id).val();
		
		$.ajax({
			url: "dashboard/products/"+id,
			method: "post",
			data: { "fieldName": "shortDescription",
				"fieldValue": shortDescVal},
			type: "json",
			success: function(product){
				console.log(product);
				$("#placehoderCard3-" + product.id).addClass("hidden");
				$("#placehoderCard4-" + product.id).removeClass("hidden");
			},
			error: function(){
				console.log("error");
			}
		});
	});

	$("body").on("click", "button[id*='addPrice']", function(){
		var id = getId($(this));
		var priceVal = $("#price-"+id).val();
		
		$.ajax({
			url: "dashboard/products/"+id,
			method: "post",
			data: { "fieldName": "price",
				"fieldValue": priceVal},
			type: "json",
			success: function(product){
				console.log(product);
				$("#placehoderCard4-" + product.id).addClass("hidden");
//				then show the finished "full added" product to the screen
				showFinishedProduct(product);
			},
			error: function(){
				console.log("error");
			}
		});
	});

	
});

function showFinishedProduct(product){
	$("#placehoderCard4-"+product.id).after("<div id=\"productCard-"+product.id+"\">" +
			"<img class=\"center-block\" src='"+product.imageUrl+"'/>" +
			"<p>"+product.shortDescription+"</p>" +
			"$" + product.price +
			"</div>");
}

function getId(obj){
	var id = $(obj).prop("id");
	return id.split("-")[1];
}

function createPlaceholderCard(){
	$("#products").append(
		"<div id=\"placehoderCard1-0\" class=\"col-xs-12 col-sm-6 col-md-3 col-lg-2 card\">"+
		"	<button id=\"createProduct-0\" class=\"btn btn-primary btn-block\">Create	product</button>"+
		"</div>"+
		"<div id=\"placehoderCard2-0\" class=\"col-xs-12 col-sm-6 col-md-3 col-lg-2 card hidden\">"+
		"	<p>Please type in the image url</p>"+
		"	<input type=\"text\" id=\"imageUrl-0\" placeholder=\"paste image url\" />"+
		"	<button id=\"addImageUrl-0\" class=\"btn btn-primary btn-block\">Next</button>"+
		"</div>");
}