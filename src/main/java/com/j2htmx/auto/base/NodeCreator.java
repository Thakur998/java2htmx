package com.j2htmx.auto.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

interface NodeRenderStrategy {
    String render(NodeCreator node);
}

class DefaultNodeRenderStrategy implements NodeRenderStrategy {

    private String buildAttributes(NodeCreator node) {
        StringBuilder sb = new StringBuilder();

        sb.append(node.classStyle);
        sb.append(node.name);
        sb.append(node.hxOn);
        sb.append(node.onload);
        sb.append(node.misc);
        sb.append(node.source);
        sb.append(node.hxSwap);
        sb.append(node.For);
        sb.append(node.style);
        sb.append(node.hxOutOfOrderSwap);
        sb.append(node.type);
        sb.append(node.hxPost);
        sb.append(node.placeholder);
        sb.append(node.id);
        sb.append(node.width);
        sb.append(node.height);
        sb.append(node.hxGet);
        sb.append(node.hxVals);
        sb.append(node.hxTarget);
        sb.append(node.hxInclude);
        sb.append(node.hxTrigger);

        return sb.toString();
    }

    @Override
    public String render(NodeCreator node) {

        String tag = node.Tag;
        String content = node.content;

        if ("img".equals(tag)) {
            return "<" + tag +
                    buildAttributes(node) +
                    " src=" + node.href +
                    "/>";
        }

        if (node.href != null && !node.href.isEmpty()) {
            return "<" + tag +
                    buildAttributes(node) +
                    " href=" + node.href +
                    ">" +
                    content +
                    "</" + tag + ">";
        }

        return "<" + tag +
                buildAttributes(node) +
                ">" +
                content +
                "</" + tag + ">";
    }
}

class StyleManager {

    private final CSSLoader cssLoader;
    private final List<String> addedStyles = new ArrayList<>();

    public StyleManager(CSSLoader loader) {
        this.cssLoader = loader;
    }

    public void addStyle(String styleClass) {
        String styleValue = cssLoader.getProperty(styleClass);

        if (styleValue != null) {
            addedStyles.add(styleValue);
        }
    }

    public String buildStyleAttribute() {
        return " style='" + String.join(" ", addedStyles) + "' ";
    }

    public String getStyle(String styleClass) {
        return cssLoader.getProperty(styleClass);
    }
}

public class NodeCreator implements Serializable, HtmxNode {

    private static final long serialVersionUID = 1L;

    String hxSwap = "";
    public String Tag = "";
    protected String misc = "";
    protected String classStyle = "";
    protected String For = "";
    protected String content = "";
    protected String hxOn = "";
    protected String placeholder = "";
    protected String source = "";
    public String layout = "";
    protected String style = "";
    protected String hxTrigger = "";
    protected String hxInclude = "";
    protected String href = "";
    protected Object clientId = "";
    protected String hxGet = "";
    protected String onload = "";
    protected String hxVals = "";
    protected String hxTarget = "";
    protected String name = "";
    protected String width = "";
    protected String hxOutOfOrderSwap = "";
    protected String type = "";
    protected String height = "";
    protected String hxPost = "";
    protected String id = "";

    private final NodeRenderStrategy renderStrategy;
    private final StyleManager styleManager;

    public NodeCreator() {

        CSSLoader loader = new CSSLoader();

        this.styleManager = new StyleManager(loader);
        this.renderStrategy = new DefaultNodeRenderStrategy();
    }

    @Override
    public int hashCode() {
        return clientId != null ? clientId.hashCode() : 0;
    }

    public void setClientId(Object obj) {
        this.clientId = obj;
    }

    public Object getClientId() {
        return clientId;
    }

    public void setTag(String tag) {
        this.Tag = tag;
    }

    public void setHxGet(String url) {
        this.hxGet = " hx-get = " + url;
    }

    public void setHxPost(String post) {
        this.hxPost = " hx-post = " + post;
    }

    public void setHxTarget(String target) {
        this.hxTarget = " hx-target = " + target;
    }

    public void setHxTrigger(String triggerCondition) {
        this.hxTrigger = " hx-trigger = " + triggerCondition;
    }

    public void setHxInclude(String validName) {
        this.hxInclude = " hx-include= " + validName;
    }

    public void setHxVals(String target) {
        this.hxVals = " hx-vals = " + target;
    }

    public void setHxSwap(String swap) {
        this.hxSwap = " hx-swap = " + swap;
    }

    public void setHxOutOfOrderSwap() {
        this.hxOutOfOrderSwap = " hx-swap-oob = true";
    }

    public void setHxOn(String triggerCondition) {
        this.hxOn = " hx-on = " + triggerCondition;
    }

    public void setSource(String url) {
        this.source = " src = " + url;
    }

    public void setOnload(String triggerCondition) {
        this.onload = " onload = " + triggerCondition;
    }

    public void setType(String type) {
        this.type = " type = " + type;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = " placeholder='" + placeholder + "'";
    }

    public void setForId(String id) {
        this.For = " for = " + id;
    }

    public void setName(String name) {
        this.name = " name= " + name + " ";
    }

    public void setNameAsReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNodeLink(String href) {
        this.href = href;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public void setStyleViaString(String styleClass) {
        this.style = styleClass;
    }

    public void setStyle(String styleClass) {
        this.style = " style='" + styleManager.getStyle(styleClass) + "' ";
        styleManager.addStyle(styleClass);
    }

    public void addStyle(String styleClass) {
        styleManager.addStyle(styleClass);
        this.style = styleManager.buildStyleAttribute();
    }

    public void setClass(String styleClass) {
        this.classStyle = " class='" + styleClass + "' ";
    }

    public void addClass(String styleClass) {

        if (classStyle == null || classStyle.isBlank()) {
            setClass(styleClass);
            return;
        }

        int end = classStyle.lastIndexOf("'");

        if (end > 0) {
            classStyle =
                    classStyle.substring(0, end)
                            + " "
                            + styleClass
                            + "'";
        }
    }

    public void setWidth(String width) {
        this.width = " width = " + width;
    }

    public void setHeight(String height) {
        this.height = " height = " + height;
    }

    public void setId(String id) {
        this.id = " id='" + id + "'";
    }

    public void setContent(NodeCreator... nodeCreators) {

        StringBuilder node = new StringBuilder();

        for (NodeCreator nodeCreator : nodeCreators) {
            node.append(nodeCreator.createPairNode());
        }

        this.content = node.toString();
    }

    public void addContent(NodeCreator... nodeCreators) {

        StringBuilder node = new StringBuilder();

        for (NodeCreator nodeCreator : nodeCreators) {
            node.append(nodeCreator.createPairNode());
        }

        this.content = this.content.concat(node.toString());
    }

    public void addContent(String... nodeCreators) {

        StringBuilder node = new StringBuilder();

        for (String nodeCreator : nodeCreators) {
            node.append(nodeCreator);
        }

        this.content = this.content.concat(node.toString());
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addContent(String content) {
        this.content = this.content.concat(content);
    }

    public String getContent() {
        return content;
    }

    public void setContentFromProperty(String property) {
        setContent(styleManager.getStyle(property));
    }

    public String createPairNode() {
        return renderStrategy.render(this);
    }

    @Override
    public String render() {
        return createPairNode();
    }
}