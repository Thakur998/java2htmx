document.body.addEventListener("htmx:afterSwap", function(evt) {

    evt.target.querySelectorAll(".lesson-column").forEach(column => {

        new Sortable(column, {
            group: "lesson-plans",
            animation: 150,
            forceFallback: true,

            onEnd: function(evt) {

                htmx.ajax(
                    "POST",
                    "/lesson/move",
                    {
                     swap: "none",
                        values: {
                            lessonId: evt.item.dataset.id,
                            status: evt.to.dataset.status,
                            position: evt.newIndex
                        }
                    }
                );

            }
        });

    });

});