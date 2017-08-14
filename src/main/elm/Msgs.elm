module Msgs exposing (..)

import Models exposing (Gender, HistoryItem, ResultValue)
import RemoteData exposing (WebData)


type Msg
    = NoOp
    | Age String
    | Height String
    | Weight String
    | Calculate
    | SetGender Gender
    | OnFetchHistory (WebData (List HistoryItem))
    | OnGetResult (WebData ResultValue)

