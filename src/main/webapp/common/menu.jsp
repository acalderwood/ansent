<%@ include file="/common/taglibs.jsp"%>
menu
<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" permissions="rolesAdapter">
<ul id="primary-nav" class="menuList">
    <menu:displayMenu name="Ubersicht"/>
    <menu:displayMenu name="Neuanlage"/>
    <menu:displayMenu name="Bearbeiten"/>
    <menu:displayMenu name="UVV"/>
    <menu:displayMenu name="Reparaturauftrag"/>
</ul>
</menu:useMenuDisplayer>