# History

Given a shopping system “Allegory” (something like eBay or Amazon) create a service that tracks average money user has spent in a system. It records short history of transactions and will report approximate average amount of money a user has spent. It has two operations:

```java
put(ID clientId, double moneys)
double get(ID clientId)
```

Clients run in separate threads. Transaction rate for this component is about 100 000 per second. 80% of operations are GETs.

Examples:

Client `A` purchased two items, for `$2` and `$26` respectively. This transactions were recorded using `put(A, 2.0)`; `put(A, 26.0)`. `get(A)` will return `14.0`.

Client `B` is a large company that makes hundreds of purchases per day. Values range from `$0.01` to `$1000.0`. `get(B)` will return average based on `N` last purchases.
