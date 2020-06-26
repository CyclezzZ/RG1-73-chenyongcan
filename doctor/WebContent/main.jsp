<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./common/head.jsp"%>
<c:if test="${empty SESSION_BEAN }">
<script type="text/javascript" language="javascript">
	top.location.href='${pageContext.request.contextPath}/exit.jsp';
</script>
</c:if>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="cache-control" content="no-store, must-revalidate"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${appTitle }</title>
<meta name="Keywords" content="${appTitle }"/>
<meta name="Description" content="${appTitle }"/> 
<%@ include file="./common/js.jsp"%>
</head>
<body>
    <!--[if lte IE 7]>
        <div id="errorie"><div>您还在使用老掉牙的IE，正常使用系统前请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
    <![endif]-->
    <div id="bjui-window">
    <header id="bjui-header">
        <div class="bjui-navbar-header">
            <button type="button" class="bjui-navbar-toggle btn-default" data-toggle="collapse" data-target="#bjui-navbar-collapse">
            </button>
            <a class="bjui-navbar-logo" href="#" style="font-size: 20px;margin-top: 10px;margin-left: 20px;color:white">${appTitle }</a>
        </div>
        <nav id="bjui-navbar-collapse">
            <ul class="bjui-navbar-right">
                <li class="datetime"><div><span id="bjui-date"></span> <span id="bjui-clock"></span></div></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">我的账户 <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                    	<li><a href="${ctx}/com/toSelfPassword.action" data-toggle="dialog" data-id="changepwd_page" data-mask="true" data-width="500" data-height="260">&nbsp;<span class="glyphicon glyphicon-lock"></span> 修改密码&nbsp;</a></li>
                        <li><a href="${ctx}/com/toSelf.action" data-toggle="dialog" data-id="changepwd_page" data-mask="true" data-width="700" data-height="460">&nbsp;<span class="glyphicon glyphicon-user"></span> 个人资料&nbsp;</a></li>
                        <li class="divider"></li>
                        <li><a href="${ctx}/com/logout.action" class="red">&nbsp;<span class="glyphicon glyphicon-off"></span> 注销登陆</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="bjui-hnav">
            <button type="button" class="btn-default bjui-hnav-more-left" title="导航菜单左移"><i class="fa fa-angle-double-left"></i></button>
            <div id="bjui-hnav-navbar-box">
                <ul id="bjui-hnav-navbar">
                	<c:if test="${SESSION_BEAN.role=='SimpleUser' }">
                    <li class=""><a href="javascript:;" data-toggle="slidebar"><i class="fa fa-cog"></i> 系统管理</a>
                        <div class="items hide" data-noinit="true">
                             <ul class="menu-items" data-faicon="table">
                                <li><a href="${ctx}/sys/queryPlace.action" data-options="{id:'mainqueryPlace', faicon:'table','fresh':true}">车位管理</a></li>
                                <li><a href="${ctx}/sys/queryEmployee.action" data-options="{id:'mainqueryEmployee', faicon:'table','fresh':true}">员工管理</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class=""><a href="javascript:;" data-toggle="slidebar"><i class="fa fa-car"></i> 车位使用管理</a>
                        <div class="items hide" data-noinit="true">
                             <ul class="menu-items" data-faicon="car">
                                <li><a href="${ctx}/sys/add2In.action" data-options="{id:'add2In', faicon:'arrow-circle-right','fresh':true}">车辆入库</a></li>
                                <li><a href="${ctx}/sys/queryPlaceUseWait.action" data-options="{id:'queryPlaceUseWait', faicon:'search','fresh':true}">等待队列查询</a></li>
                                <li><a href="${ctx}/sys/add2Out.action" data-options="{id:'add2Out', faicon:'arrow-circle-left','fresh':true}">车辆出库</a></li>
                                <li><a href="${ctx}/sys/queryPlaceUse.action" data-options="{id:'mainqueryPlaceUse', faicon:'search','fresh':true}">车辆查询</a></li>
                                <li><a href="${ctx}/sys/anlPlace.action" data-options="{id:'anlPlace', faicon:'line-chart','fresh':true}">车位使用统计</a></li>
                            </ul>
                        </div>
                    </li>
                    </c:if>
                    
                    
                	<c:if test="${SESSION_BEAN.role=='SysUser' }">
                    <li class=""><a href="javascript:;" data-toggle="slidebar"><i class="fa fa-cog"></i> 系统管理</a>
                        <div class="items hide" data-noinit="true">
                             <ul class="menu-items" data-faicon="table">
                                <li><a href="${ctx}/sys/querySimpleUser.action" data-options="{id:'mainquerySimpleUser', faicon:'table','fresh':true}">操作员管理</a></li>
                                <li><a href="${ctx}/sys/queryPlace.action" data-options="{id:'mainqueryPlace', faicon:'table','fresh':true}">车位管理</a></li>
                                <li><a href="${ctx}/sys/queryEmployee.action" data-options="{id:'mainqueryEmployee', faicon:'table','fresh':true}">员工管理</a></li>
                            </ul>
                        </div>
                    </li>
                    </c:if>
                    <li class="active"><a href="javascript:;" data-toggle="slidebar"><i class="fa fa-user"></i> 个人信息</a>
	                        <div class="items hide" data-noinit="true">
	                        
	                             <ul class="menu-items" data-faicon="table">
	                                <li><a href="${ctx}/com/toSelf.action" data-options="{id:'toSelf', faicon:'user','fresh':true}">个人资料</a></li>
	                            </ul>
	                        </div>
	                </li>
                </ul>
            </div>
            <button type="button" class="btn-default bjui-hnav-more-right" title="导航菜单右移"><i class="fa fa-angle-double-right"></i></button>
        </div>
    </header>
    <div id="bjui-container">
        <div id="bjui-leftside">
            <div id="bjui-sidebar-s">
                <div class="collapse"></div>
            </div>
            <div id="bjui-sidebar">
                <div class="toggleCollapse"><h2><i class="fa fa-bars"></i> 导航栏 <i class="fa fa-bars"></i></h2><a href="javascript:;" class="lock"><i class="fa fa-lock"></i></a></div>
                <div class="panel-group panel-main" data-toggle="accordion" id="bjui-accordionmenu" data-heightbox="#bjui-sidebar" data-offsety="26">
                </div>
            </div>
        </div>
        <div id="bjui-navtab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent">
                    <ul class="navtab-tab nav nav-tabs">
                        <li data-url="content.jsp"><a href="javascript:;"><span><i class="fa fa-home"></i> #maintab#</span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft"><i class="fa fa-angle-double-left"></i></div>
                <div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
                <div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">#maintab#</a></li>
            </ul>
            <div class="navtab-panel tabsPageContent">
                <div class="navtabPage unitBox">
                    <div class="bjui-pageContent" style="background:#FFF;">
                        Loading...
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer id="bjui-footer">Copyright &copy; 2019 ${appTitle }</a>　
    </footer>
    </div>
</body>
</html>