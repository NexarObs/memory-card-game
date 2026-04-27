(function () {

    // STATE
    let firstCard = null;
    let secondCard = null;
    let locked = false;
    let triesLeft = parseInt(document.getElementById("triesLeft").textContent);
    let matchedPairs = 0;
    const totalPairs = document.querySelectorAll(".card").length / 2;

    // Restore already matched cards on load
    document.querySelectorAll(".card").forEach(card => {
        if (card.dataset.matched === "true") {
            card.classList.add("matched");
            matchedPairs++;
        }
    });

    // FLIP HANDLER
    window.handleFlip = function (card) {
        if (locked) return;
        if (card.classList.contains("matched")) return;
        if (card.classList.contains("flipped")) return;

        card.classList.add("flipped");

        if (!firstCard) {
            firstCard = card;
            return;
        }

        secondCard = card;
        locked = true;

        checkMatch();
    };

    // MATCH DETECTION
    function checkMatch() {
        const isMatch = firstCard.dataset.value === secondCard.dataset.value;

        if (isMatch) {
            firstCard.classList.add("matched");
            secondCard.classList.add("matched");
            firstCard.dataset.matched = "true";
            secondCard.dataset.matched = "true";
            matchedPairs++;
            resetTurn();

            if (matchedPairs === totalPairs) {
                setTimeout(() => endGame("won"), 500);
            }
        } else {
            triesLeft--;
            updateTriesDisplay();

            firstCard.classList.add("shake");
            secondCard.classList.add("shake");

            if (triesLeft <= 0) {
                setTimeout(() => endGame("lost"), 800);
            } else {
                setTimeout(() => {
                    firstCard.classList.remove("flipped", "shake");
                    secondCard.classList.remove("flipped", "shake");
                    resetTurn();
                }, 1000);
            }
        }
    }

    // RESET TURN
    function resetTurn() {
        firstCard = null;
        secondCard = null;
        locked = false;
    }

    // UPDATE TRIES DISPLAY
    function updateTriesDisplay() {
        document.getElementById("triesLeft").textContent = triesLeft;
    }

    // SERIALIZE BOARD STATE
    function serializeBoard() {
        const cards = [];
        document.querySelectorAll(".card").forEach(card => {
            cards.push({
                index: parseInt(card.dataset.index),
                value: card.dataset.value,
                matched: card.dataset.matched === "true",
                flipped: false
            });
        });

        return JSON.stringify({
            cards: cards,
            triesLeft: triesLeft,
            totalTries: TOTAL_TRIES,
            difficulty: document.querySelector("[data-difficulty]")?.dataset.difficulty || "",
            gridSize: Math.sqrt(cards.length)
        });
    }

    // SAVE GAME
    window.saveGame = function () {
        const boardState = serializeBoard();

        fetch("/games/save", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `gameId=${GAME_ID}&boardState=${encodeURIComponent(boardState)}&triesLeft=${triesLeft}`
        })
            .then(res => res.text())
            .then(result => {
                if (result === "saved") {
                    window.location.href = "/games";
                }
            });
    };



    // END GAME (win or lose)
    function endGame(outcome) {
        fetch("/games/end", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `gameId=${GAME_ID}`
        })
            .then(res => res.text())
            .then(() => {
                if (outcome === "won") {
                    window.location.href = "/games?result=won";
                } else {
                    window.location.href = "/games?result=lost";
                }
            });
    }

})();