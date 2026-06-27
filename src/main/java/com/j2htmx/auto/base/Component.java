package com.j2htmx.auto.base;


import com.j2htmx.auto.components.Form;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Component extends NodeCreator {

    public Component clazz(String clazz) {
        setClass(clazz);
        return this;
    }

    public Component as(String tag) {

        setTag(tag);

        return this;
    }

    public Component addClazz(String clazz) {
        addClass(clazz);
        return this;
    }

    public Component style(String style) {
        setStyle(style);
        return this;
    }

    public Component styleRaw(String style) {
        setStyleViaString(style);
        return this;
    }

    public Component above(int size) {

        addClass("j2-mt-" + size);

        return this;
    }


    public Component customTag(String key, String value) {
        customTags.add(" " + key + "=\"" + value + "\"");
        return this;
    }

    public Component customTag(String key) {
        customTags.add(" " + key + "=\"");
        return this;
    }

    public Component below(int size) {

        addClass("j2-mb-" + size);

        return this;
    }


    public Component id(String id) {
        setId(id);
        setRawId(id);
        return this;
    }

    public Component name(String name) {
        setName(name);
        return this;
    }

    public Component type(String type) {
        setType(type);
        return this;
    }

    public Component placeholder(String placeholder) {
        setPlaceholder(placeholder);
        return this;
    }

    public Component width(String width) {
        setWidth(width);
        return this;
    }

    public Component height(String height) {
        setHeight(height);
        return this;
    }

    public Component href(String href) {
        setNodeLink(href);
        return this;
    }

    public Component include(String selector) {
        setHxInclude(selector);
        return this;
    }

    public Component vals(Map<String, ?> values) {

        try {

            ObjectMapper mapper =
                    new ObjectMapper();

            setHxVals(
                    mapper.writeValueAsString(values)
            );

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return this;
    }

    public Component on(String event) {
        setHxOn(event);
        return this;
    }

    public Component oob() {
        setHxOutOfOrderSwap();
        return this;
    }

    public Component source(String source) {
        setSource(source);
        return this;
    }

    public Component content(String content) {
        setContent(content);
        return this;
    }

    public Component content(NodeCreator... nodes) {
        setContent(nodes);
        return this;
    }

    public Component add(NodeCreator... nodes) {
        addContent(nodes);
        return this;
    }

    public Component add(String content) {
        addContent(content);
        return this;
    }

    public Component grid() {
        addClass("j2-grid");
        return this;
    }

    public Component nextRow() {
        addClass("j2-next-row");
        return this;
    }

    public Component grid(int columns) {
        addClass("j2-grid-" + columns);
        return this;
    }

    public Component gridAuto() {
        addClass("j2-grid-auto");
        return this;
    }


    public Component justifyStart() {
        addClass("j2-justify-start");
        return this;
    }


    public Component justifyEnd() {
        addClass("j2-justify-end");
        return this;
    }


    public Component justifyAround() {
        addClass("j2-justify-around");
        return this;
    }

    public Component justifyEvenly() {
        addClass("j2-justify-evenly");
        return this;
    }

    public Component alignStart() {
        addClass("j2-align-start");
        return this;
    }

    public Component alignEnd() {
        addClass("j2-align-end");
        return this;
    }

    public Component alignStretch() {
        addClass("j2-align-stretch");
        return this;
    }

    public Component fullWidth() {
        addClass("j2-w-full");
        return this;
    }

    public Component halfWidth() {
        addClass("j2-w-half");
        return this;
    }

    public Component thirdWidth() {
        addClass("j2-w-third");
        return this;
    }

    public Component quarterWidth() {
        addClass("j2-w-quarter");
        return this;
    }

    public Component autoWidth() {
        addClass("j2-w-auto");
        return this;
    }
    public Component fullHeight() {
        addClass("j2-h-full");
        return this;
    }

    public Component screenHeight() {
        addClass("j2-h-screen");
        return this;
    }

    public Component container() {
        addClass("j2-container");
        return this;
    }

    public Component containerSm() {
        addClass("j2-container-sm");
        return this;
    }

    public Component containerMd() {
        addClass("j2-container-md");
        return this;
    }

    public Component containerLg() {
        addClass("j2-container-lg");
        return this;
    }

    public Component containerXl() {
        addClass("j2-container-xl");
        return this;
    }

    public Component textLeft() {
        addClass("j2-text-left");
        return this;
    }

    public Component textCenter() {
        addClass("j2-text-center");
        return this;
    }

    public Component textRight() {
        addClass("j2-text-right");
        return this;
    }

    public Component bold() {
        addClass("j2-bold");
        return this;
    }

    public Component rowSpan(int rows) {
        addClass("j2-row-span-" + rows);
        return this;
    }

    public Component newRow() {
        addClass("j2-new-row");
        return this;
    }

    public Component colStart(int col) {
        addClass("j2-col-start-" + col);
        return this;
    }
    public Component rowStart(int row) {
        addClass("j2-row-start-" + row);
        return this;
    }

    public Component pushRight() {
        addClass("j2-push-right");
        return this;
    }
    public Component pushBottom() {
        addClass("j2-push-bottom");
        return this;
    }
    public Component order(int order) {
        addClass("j2-order-" + order);
        return this;
    }
    public Component colSpan(int cols) {
        addClass("j2-col-span-" + cols);
        return this;
    }
    public Component grow() {
        addClass("j2-grow");
        return this;
    }
    public Component shrink() {
        addClass("j2-shrink");
        return this;
    }
    public Component italic() {
        addClass("j2-italic");
        return this;
    }
    public Component hidden() {
        addClass("j2-hidden");
        return this;
    }

    public Component block() {
        addClass("j2-block");
        return this;
    }

    public Component inline() {
        addClass("j2-inline");
        return this;
    }

    public Component inlineBlock() {
        addClass("j2-inline-block");
        return this;
    }
    public Component get(String url) {
        setHxGet(url);
        return this;
    }

    public Component post(String url) {
        setHxPost(url);
        return this;
    }

    public Component target(String target) {
        setHxTarget("#"+target);
        return this;
    }

    public Component swap(String swap) {
        setHxSwap(swap);
        return this;
    }

    public Component trigger(String trigger) {
        setHxTrigger(trigger);
        return this;
    }
    public Component target(NodeCreator node) {

        if (node.id == null) {
            throw new RuntimeException(
                    "Target component must have id"
            );
        }

        setHxTarget("#" + node.rawId);

        return this;
    }

    public Component inner() {

        setHxSwap("innerHTML");

        return this;
    }

    public Component outer() {

        setHxSwap("outerHTML");

        return this;
    }


    public Component before() {

        setHxSwap("beforebegin");

        return this;
    }

    public Component after() {

        setHxSwap("afterend");

        return this;
    }

    public Component delete() {

        setHxSwap("delete");

        return this;
    }
    public Component replaceContent() {
        setHxSwap("innerHTML");
        return this;
    }

    public Component replaceOuter() {
        setHxSwap("outerHTML");
        return this;
    }

    public Component append() {
        setHxSwap("beforeend");
        return this;
    }

    public Component prepend() {
        setHxSwap("afterbegin");
        return this;
    }

    public Component appendTo(NodeCreator node) {

        target(node);

        append();

        return this;
    }


    public Component centerScreen() {
        addClass("j2-center-screen");
        return this;
    }

    public Component with(
            NodeCreator... components) {

        String include =
                Arrays.stream(components)
                        .map(component ->
                                "#" + component.rawId)
                        .collect(
                                Collectors.joining(",")
                        );

        setHxInclude(include);

        return this;
    }

    public Component with(
            String... components) {

        String include =
                Arrays.stream(components)
                        .map(component ->
                                "#" + component)
                        .collect(
                                Collectors.joining(",")
                        );

        setHxInclude(include);

        return this;
    }

    public Component with(Form form) {

        return with(
                (NodeCreator) form
        );
    }

    public Component replace(NodeCreator target) {

        target(target);
        outer();

        return this;
    }

    public Component update(NodeCreator target) {

        target(target);
        inner();

        return this;
    }

    public Component prependTo(NodeCreator target) {

        target(target);
        prepend();

        return this;
    }
    public Component onClick() {

        setHxTrigger("click");

        return this;
    }

    public Component onChange() {

        setHxTrigger("change");

        return this;
    }

    public Component onLoad() {

        setHxTrigger("load");

        return this;
    }

    public Component onSubmit() {

        setHxTrigger("submit");

        return this;
    }
    public Component onTrigger(String event) {
        setHxTrigger(event);
        return this;
    }

    public Component bottom(int px) {

        return css(
                "bottom",
                px + "px"
        );
    }

    public Component top(int px) {

        return css(
                "top",
                px + "px"
        );
    }

    public Component left(int px) {

        return css(
                "left",
                px + "px"
        );
    }

    public Component right(int px) {

        return css(
                "right",
                px + "px"
        );
    }

    public enum HxTrigger {

        CLICK("click"),
        CHANGE("change"),
        SUBMIT("submit"),
        LOAD("load"),

        KEYUP("keyup"),
        KEYDOWN("keydown"),
        KEYPRESS("keypress"),

        BLUR("blur"),
        FOCUS("focus"),

        MOUSE_ENTER("mouseenter"),
        MOUSE_LEAVE("mouseleave"),

        REVEALED("revealed"),
        INTERSECT("intersect");

        private final String value;

        HxTrigger(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    public Component onTrigger(HxTrigger trigger) {

        setHxTrigger(trigger.value());

        return this;
    }





    public void addInlineStyle(String style) {

        if (this.style == null || this.style.isBlank()) {

            this.style =
                    " style='" + style + "' ";

            return;
        }

        int end =
                this.style.lastIndexOf("'");

        if (end > 0) {

            this.style =
                    this.style.substring(0, end)
                            + style
                            + "'";
        }
    }

    public Component css(
            String property,
            String value) {

        addInlineStyle(
                property + ":" + value + ";"
        );

        return this;
    }

    public Component x(int px) {

        return css(
                "left",
                px + "px"
        );
    }

    public Component y(int px) {

        return css(
                "top",
                px + "px"
        );
    }

    public Component width(int px) {

        return css(
                "width",
                px + "px"
        );
    }

    public Component height(int px) {

        return css(
                "height",
                px + "px"
        );
    }

    public Component absolute() {

        return css(
                "position",
                "absolute"
        );
    }

    public Component relative() {

        return css(
                "position",
                "relative"
        );
    }

    public Component fixed() {

        return css(
                "position",
                "fixed"
        );
    }


    public Component z(int value) {

        return css(
                "z-index",
                String.valueOf(value)
        );
    }

    public Component centerX() {

        css(
                "left",
                "50%"
        );

        css(
                "transform",
                "translateX(-50%)"
        );

        return this;
    }

    public Component centerY() {

        css(
                "top",
                "50%"
        );

        css(
                "transform",
                "translateY(-50%)"
        );

        return this;
    }

    public Component center() {

        css(
                "left",
                "50%"
        );

        css(
                "top",
                "50%"
        );

        css(
                "transform",
                "translate(-50%,-50%)"
        );

        return this;
    }

    public Component row() {

        css(
                "display",
                "flex"
        );

        return this;
    }

    public Component column() {

        css(
                "display",
                "flex"
        );

        css(
                "flex-direction",
                "column"
        );

        return this;
    }

    public Component alignCenter() {

        css(
                "align-items",
                "center"
        );

        return this;
    }

    public Component justifyCenter() {

        css(
                "justify-content",
                "center"
        );

        return this;
    }

    public Component centerContent() {

        return row()
                .alignCenter()
                .justifyCenter();
    }

    public Component gap(int px) {

        return css(
                "gap",
                px + "px"
        );
    }

    public Component padding(int px) {

        return css(
                "padding",
                px + "px"
        );
    }

    public Component margin(int px) {

        return css(
                "margin",
                px + "px"
        );
    }

    public Component fullscreen() {

        return fixed()
                .x(0)
                .y(0)
                .css("width", "100vw")
                .css("height", "100vh");
    }

    public Component justifyBetween() {

        return css(
                "justify-content",
                "space-between"
        );
    }

    public Component text(String text) {

        setContent(text);

        return this;
    }

    public Component bringToFront() {

        addClass("bring-to-front");

        return this;
    }


}

