/**

 
 */
 
 
layui.define(['mm','jquery'],function(exports){
  var $ = layui.$,mm = layui.mm;
  var menu = {
    init: function(){
      $('.menu').on('click',function(){
        if($(this).hasClass('on')){
          $(this).removeClass('on')
          $('.header-down-nav').removeClass('layui-show');
        }else{
          $(this).addClass('on')
          $('.header-down-nav').addClass('layui-show');
        }
      })
      window.onresize = function () {
        var curwidth = document.documentElement.clientWidth;
        if(curwidth > 760){
          $('.header-down-nav').removeClass('layui-show');
          $('.menu').removeClass('on');
        }
      };
    var count = $('.list-cont .cont').length;
    $('.volume span').text(count);
    $('.op-list .like').on('click',function(){
      var oSpan =  $(this).children('span');
      var num = parseInt($(oSpan).text())
      var off = $(this).attr('off')
        if(off){
          $(this).removeClass('active');
          off = true;
          $(oSpan).text(num-1)
          $(this).attr('off','')
        }else{
          $(this).addClass('active');
          off = false;
          $(oSpan).text(num+1)
          $(this).attr('off','true')
        }
      })
    },

    submit: function(){
      $('.definite').on('click',function(e){
        var event = e || event;
        event.preventDefault();
        var $listcont = $(this).parents('.form').siblings('.list-cont').length ? $(this).parents('.form').siblings('.list-cont') : $(this).parents('.form-box').siblings('.list-cont');
        console.log($listcont)
        var img = $(this).parents('form').siblings('img').attr('src');
        var textarea = $(this).parents('.layui-form-item').siblings('.layui-form-text').children('.layui-input-block').children('textarea');
        var name = $(textarea).val();
        var html = laytplCont.innerHTML;
        var data = {
          avatar : img,
          name : '匿名用户',
          cont : name,
        }
        if(name){
          var cont = mm.renderHtml(html,data);
          $listcont.prepend(cont);
          var cunt = $(this).parents('.form-box').siblings('.volume').children('span');
          var cunts = $(this).parents('.form-box').siblings('.list-cont').children('.cont').length;
         /* textarea.val('')*/
        }else{
          layer.msg('请输入内容')
        } 
        cunt.text(cunts);
      })
    }
  }     
  exports('menu',menu)
});