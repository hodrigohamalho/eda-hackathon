Prometheus

```helm template -f prometheus/values.yaml prometheus | oc apply -f-```

Grafana

```helm template -f grafana/values.yaml grafana | oc apply -f-```