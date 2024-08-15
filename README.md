# Android Interview - Expensis

The following project consists of a base Android app representing a bare framework for a card and an expense management application.

## UI/UX Task:

- [Card list item component](documentation/CardListItem.png) on the home screen (using the `card-*` assets)

## Other Task:

- Fetch cards from an API

GET [https://api.jsonbin.io/v3/b/65943ceadc746540188c0898?meta=false](https://api.jsonbin.io/v3/b/65943ceadc746540188c0898?meta=false)

Header Parameters: <br>
```plaintext
X-Access-Key : $2a$10$/xCFTGtLDGVE4lvlqrw5nO8jH47.ZKMsHymFl9Y3tVSOnaEjEiKI.
```

Response
```json
{
    "cards": [
        {
            "id": "1",
            "pan": "8343574823327434",
            "name": "JOVAN UPSHAW",
            "currency": "DKK",
            "limit": 1000,
            "available": 603.15
        },
    ]
}
```