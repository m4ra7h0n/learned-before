var $ = mdui.$;
var tab = new mdui.Tab('#example5-tab');

$('#example-5').on('open.mdui.dialog', function () {
  tab.handleUpdate();
});
