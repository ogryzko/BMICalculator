module Commands exposing (..)

import Http exposing (request, emptyBody)
import Json.Decode as Decode
import Json.Decode.Pipeline exposing (decode, required)
import QueryString as QS
import Models exposing (HistoryItem, Input)
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
       if validateInput input then
        Http.post (calculateQueryString input) emptyBody historyItemDecoder
            |> RemoteData.sendRequest
            |> Cmd.map Msgs.OnGetResult
        else
            Cmd.none


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
                |> QS.add "gender" input.gender
                |> QS.render
    in
        (calculateUrl ++ query)


validateInput : Input -> Bool
validateInput input =
   (input.age <= 120) && (input.age > 0)  && (input.height < 300) && (input.height > 0) && (input.weight < 570) && (input.weight > 0)


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