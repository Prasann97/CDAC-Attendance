document.addEventListener("DOMContentLoaded", function () {
		const loaderWrapper = document.getElementById("loaderImage");
		const content = document.getElementById("content");

		setTimeout(function () {
			loaderWrapper.style.opacity = "1"; // Fade in the loader
			loaderWrapper.style.visibility = "visible";
			content.style.opacity = "0"; // Hide the content
		}, 0);

		setTimeout(function () {
			loaderWrapper.style.opacity = "0"; // Fade out the loader
			loaderWrapper.style.visibility = "hidden";
			content.style.opacity = "1"; // Show the content
		}, 2000); // Show for 5 seconds (5000 milliseconds)
	});