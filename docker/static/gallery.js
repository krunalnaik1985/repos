var storetext;

function upDate(previewPic)
{
   var element = document.getElementById('image');
   //alert(previewPic.src);
   element.style.backgroundImage = "url('" + previewPic.src + "')";
   storetext = document.getElementById('image').innerHTML;
   element.style.color = 'red';
   //alert(storetext);
   //alert(previewPic.alt);
   document.getElementById('image').innerHTML = previewPic.alt;
}

function unDo()
{
    document.getElementById("image").style.backgroundImage = "url('')";
    document.getElementById('image').innerHTML = storetext;
    document.getElementById("image").style.color = 'yellow';
}

