let highestZ = 1000;

function makeWindowDraggable(windowEl) {

    const titleBar =
        windowEl.querySelector(".window-title");

    if (!titleBar) {
        return;
    }

    let dragging = false;

    let startX = 0;
    let startY = 0;

    let startLeft = 0;
    let startTop = 0;

    titleBar.addEventListener(
        "mousedown",
        function (e) {

            dragging = true;

            highestZ++;

            windowEl.style.zIndex =
                highestZ;

            startX =
                e.clientX;

            startY =
                e.clientY;

            startLeft =
                parseInt(
                    windowEl.style.left || 0
                );

            startTop =
                parseInt(
                    windowEl.style.top || 0
                );

            document.body.style.userSelect =
                "none";
        }
    );

    document.addEventListener(
        "mousemove",
        function (e) {

            if (!dragging) {
                return;
            }

            const dx =
                e.clientX - startX;

            const dy =
                e.clientY - startY;

            windowEl.style.left =
                startLeft + dx + "px";

            windowEl.style.top =
                startTop + dy + "px";
        }
    );

    document.addEventListener(
        "mouseup",
        function () {

            dragging = false;

            document.body.style.userSelect =
                "";
        }
    );
}

function initializeWindows() {

    document
        .querySelectorAll(
            ".draggable-window"
        )
        .forEach(
            makeWindowDraggable
        );
}

let highestZIndex = 1000;

function initializeBringToFront() {

    document
        .querySelectorAll(".bring-to-front")
        .forEach(element => {

            if (element.dataset.btfInit) {
                return;
            }

            element.dataset.btfInit = "true";

            element.addEventListener(
                "mousedown",
                () => {

                    highestZIndex++;

                    element.style.zIndex =
                        highestZIndex;
                }
            );
        });
}


function initializeDesktop() {

    initializeWindows();

    initializeBringToFront();

    initializeMaximizeButtons();
}

document.addEventListener(
    "DOMContentLoaded",
    initializeDesktop
);

document.body.addEventListener(
    "htmx:afterSwap",
    initializeDesktop
);
function initializeMaximizeButtons() {

    document
        .querySelectorAll(".window-maximize")
        .forEach(button => {

            if (button.dataset.initialized) {
                return;
            }

            button.dataset.initialized = "true";

            button.addEventListener(
                "click",
                e => {

                    e.stopPropagation();

                    const windowEl =
                        button.closest(".window");

                    windowEl.classList.toggle(
                        "window-maximized"
                    );
                }
            );
        });
}

document
    .querySelectorAll(
        ".dock-icon"
    )
    .forEach(icon => {

        icon.addEventListener(
            "click",
            () => {

                const id =
                    icon.dataset.app;

                const windowEl =
                    document.getElementById(id);

                if (!windowEl) {
                    return;
                }

                windowEl.style.display =
                    "block";

                windowEl.style.zIndex =
                    ++highestZIndex;
            }
        );
    });

function initializeMaximizeButtons() {

    document
        .querySelectorAll(
            ".window-maximize"
        )
        .forEach(button => {

            if (
                button.dataset.maxInit
            ) {
                return;
            }

            button.dataset.maxInit =
                "true";

            button.addEventListener(
                "click",
                e => {

                    e.stopPropagation();

                    const windowEl =
                        button.closest(
                            ".window"
                        );

                    if (!windowEl) {
                        return;
                    }

                    windowEl.classList.toggle(
                        "window-maximized"
                    );
                }
            );
        });
}

document.addEventListener("DOMContentLoaded", () => {

    const desktop = document.getElementById("desktop");

    console.log("desktop found:", desktop);

    desktop.addEventListener("contextmenu", function (e) {

        console.log("RIGHT CLICK TRIGGERED");

        e.preventDefault();

        fetch("/desktop/context-menu")
            .then(r => r.text())
            .then(html => {
                console.log("SERVER RESPONSE:", html);

                const old = document.getElementById("desktop-context-menu");
                if (old) old.remove();

                document.body.insertAdjacentHTML("beforeend", html);

                const menu = document.getElementById("desktop-context-menu");

                menu.style.position = "fixed";
                menu.style.left = e.clientX + "px";
                menu.style.top = e.clientY + "px";
                menu.style.display = "block";
            });
    });
});

document.addEventListener("contextmenu", (e) => {
       e.preventDefault();

       fetch("/desktop/context-menu")
                   .then(r => r.text())
                   .then(html => {
                       console.log("SERVER RESPONSE:", html);

                       const old = document.getElementById("context-menu");
                       if (old) {
                         document.querySelectorAll(".context-menu").forEach(el => el.remove());
                       }

                       document.body.insertAdjacentHTML("beforeend", html);

                      const menu = document.getElementById("context-menu");

                      menu.style.left = `${e.pageX}px`;
                      menu.style.top = `${e.pageY}px`;


                               // force reflow so animation triggers correctly
                                  menu.getBoundingClientRect();

                                  menu.classList.add("show");

                                  // attach outside click handler
                                  attachOutsideClick(menu);
                                  if (window.htmx) {
                                      htmx.process(menu);
                                  }

                      requestAnimationFrame(() => {
                          const rect = menu.getBoundingClientRect();

                          if (rect.right > window.innerWidth) {
                              menu.style.left = `${window.innerWidth - rect.width - 10}px`;
                          }

                          if (rect.bottom > window.innerHeight) {
                              menu.style.top = `${window.innerHeight - rect.height - 10}px`;
                          }
                      });


                   });



});

function attachOutsideClick(menu) {

    document.addEventListener("mousedown", (e) => {

        const menu = document.getElementById("context-menu");
        if (!menu) return;

        const clickedItem = e.target.closest(".context-menu-item");
        const clickedInsideMenu = menu.contains(e.target);

        // CASE 1: click outside → close immediately
        if (!clickedInsideMenu) {
            menu.remove();
            return;
        }

        // CASE 2: click inside → delay close
        if (clickedItem) {
            setTimeout(() => {
                const m = document.getElementById("context-menu");
                if (m) m.remove();
            }, 120); // small UX delay
        }
    });
}



 async function openBrowser() {
        const res = await fetch("/api/open", { method: "GET" });
        if (res.ok) {
            window.browserAPI.ready();
        }
    }

requestAnimationFrame(() => requestAnimationFrame(openBrowser));

function updateClock() {

    const clock =
        document.getElementById("topbar-clock");

    if (!clock) {
        return;
    }

    const now =
        new Date();

    const time =
        now.toLocaleTimeString(
            [],
            {
                hour: "2-digit",
                minute: "2-digit",
                second: "2-digit"
            }
        );

    const date =
        now.toLocaleDateString(
            [],
            {
                weekday: "short",
                day: "2-digit",
                month: "short"
            }
        ).toUpperCase();

    clock.innerHTML = `
        <div class="clock-time">${time}</div>
        <div class="clock-date">${date}</div>
    `;
}

updateClock();

setInterval(
    updateClock,
    1000
);

function initializeSlideshows(){

    document
        .querySelectorAll(".slideshow")
        .forEach(slideshow=>{

            if(slideshow.dataset.init){
                return;
            }

            slideshow.dataset.init="true";

            const slides=
                slideshow.querySelectorAll(".slide");

            let index=0;

            setInterval(()=>{

                slides[index]
                    .classList
                    .remove("active");

                index=
                    (index+1)%slides.length;

                slides[index]
                    .classList
                    .add("active");

            },3000);

        });

}

const canvas = document.getElementById("fractal-window");
const ctx = canvas.getContext("2d");

ctx.imageSmoothingEnabled = false;

const img = new Image();

function update() {

    img.onload = function () {

        ctx.clearRect(0, 0, canvas.width, canvas.height);

        ctx.drawImage(
            img,
            0,
            0,
            canvas.width,
            canvas.height
        );
    };

    img.src = "/fractal.png?t=" + performance.now();
}

setInterval(update, 33);

document.addEventListener(
    "DOMContentLoaded",
    initializeSlideshows
);

document.body.addEventListener(
    "htmx:afterSwap",
    initializeSlideshows
);