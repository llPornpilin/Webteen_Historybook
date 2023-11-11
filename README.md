# Webteen_Historybook
## Rabbit MQ
* Get Book
  * Queue -> GetBookQueue
  * Exchange -> HistoryExchange
  * Routing key -> getBook
* Get History
  * Queue -> GetHistoryQueue
  * Exchange -> HistoryExchange
  * Routing key -> getHistory
* Add History
  * Queue -> AddHistoryQueue
  * Exchange -> HistoryExchange
  * Routing key -> addHistory
## Path
* Get Book
  * http://localhost:8082/history-service/getBook
* Get History
  * http://localhost:8082/history-service/getHistory
* Add History
  * http://localhost:8082/history-service/addHistory
