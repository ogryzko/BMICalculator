module Commands exposing (..)

import Http exposing (request)
import Json.Decode as Decode
import Json.Decode.Pipeline exposing (decode, required)
import Models exposing (HistoryItem)
import Msgs exposing (Msg)
import RemoteData


fetchHistory : Cmd Msg
fetchHistory =
    Http.get fetchHistoryUrl historyDecoder
        |> RemoteData.sendRequest
        |> Cmd.map Msgs.OnFetchHistory


fetchHistoryUrl : String
fetchHistoryUrl =
    "http://localhost:3000/history"



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
