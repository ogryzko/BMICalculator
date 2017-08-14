module Commands exposing (..)

import Http exposing (request)
import Json.Decode as Decode
import Json.Decode.Pipeline exposing (decode, required)
import QueryString as QS
import Models exposing (HistoryItem, Input, ResultValue)
import Msgs exposing (Msg)
import RemoteData


fetchHistory : Cmd Msg
fetchHistory =
    Http.get fetchHistoryUrl historyDecoder
        |> RemoteData.sendRequest
        |> Cmd.map Msgs.OnFetchHistory


fetchHistoryUrl : String
fetchHistoryUrl =
    "/history"

calculateCmd : Input -> Cmd Msg
calculateCmd input =
       Http.get (calculateQueryString input) resultDecoder
           |> RemoteData.sendRequest
           |> Cmd.map Msgs.OnGetResult

calculateUrl : String
calculateUrl =
    "/calculator"

calculateQueryString : Input -> String
calculateQueryString input =
    let
        query =
            QS.empty
                |> QS.add "weight" (toString input.weight)
                |> QS.add "height" (toString input.height)
                |> QS.add "age" (toString input.age)
                |> QS.add "gender" (toString input.gender)
                |> QS.render
    in
        (calculateUrl ++ query)






-- DECODERS


historyDecoder : Decode.Decoder (List HistoryItem)
historyDecoder =
    Decode.list historyItemDecoder


historyItemDecoder : Decode.Decoder HistoryItem
historyItemDecoder =
    decode HistoryItem
        |> required "id" Decode.int
        |> required "date" Decode.string
        |> required "age" Decode.int
        |> required "gender" Decode.string
        |> required "height" Decode.float
        |> required "weight" Decode.float
        |> required "bmi" Decode.float
        |> required "pi" Decode.float
        |> required "kind" Decode.string

resultDecoder : Decode.Decoder ResultValue
resultDecoder =
    decode ResultValue
        |> required "bmi" Decode.float
        |> required "pi" Decode.float
        |> required "kind" Decode.string
